package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutRequest;
import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutResponse;
import com.codingshuttle.projects.loveable_clone.dto.subscription.PortalResponse;
import com.codingshuttle.projects.loveable_clone.entity.Plan;
import com.codingshuttle.projects.loveable_clone.entity.User;
import com.codingshuttle.projects.loveable_clone.error.ResourceNotFoundException;
import com.codingshuttle.projects.loveable_clone.repository.PlanRepository;
import com.codingshuttle.projects.loveable_clone.repository.UserRepository;
import com.codingshuttle.projects.loveable_clone.security.AuthUtil;
import com.codingshuttle.projects.loveable_clone.service.PaymentProcesser;
import com.stripe.exception.StripeException;
import com.stripe.model.StripeObject;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j

@Service
@RequiredArgsConstructor
public class StripePaymentProcessor implements PaymentProcesser {

    @Value("${client.url}")
    private String fontendUrl;

    private final AuthUtil authUtil;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        Plan plan = planRepository.findById(request.planId()).orElseThrow(() ->
                new ResourceNotFoundException("Plan", request.planId().toString()));

        Long userId = authUtil.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("user",userId.toString()));

        var params = SessionCreateParams.builder()
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setPrice(plan.getStripePriceId()).setQuantity(1L).build())
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .setSubscriptionData(
                        new SessionCreateParams.SubscriptionData.Builder()
                                .setBillingMode(SessionCreateParams.SubscriptionData.BillingMode.builder()
                                        .setType(SessionCreateParams.SubscriptionData.BillingMode.Type.FLEXIBLE)
                                        .build())
                                .build()
                )
                .setSuccessUrl(fontendUrl + "/success.html?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(fontendUrl + "/cancel.html")
                .putMetadata("user_id", userId.toString())
                .putMetadata("plan_id",plan.getId().toString());

        try {

            String stripeCustomerId = user.getStripeCustomerId();

            if(stripeCustomerId == null || stripeCustomerId.isEmpty()){
                params.setCustomerEmail(user.getUsername());
            }else{
                params.setCustomer(stripeCustomerId);
            }

            Session session = Session.create(params.build());
            return new CheckoutResponse(session.getUrl());

        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }

    @Override
    public void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata) {
        log.debug("Handling stripe event: {}",type);

        switch (type){
            case "checkout.session.completed" -> handleCheckoutSessionCompleted(); // one-time , on checkout completed
            case "customer.subscription.update" -> handleCustomerSubscriptionUpdated(); // when user cancels, upgrades or any updates
            case "customer.subscription.delete" -> handleCustomerSubscriptionDeleted(); // whne subscription end , revoke the service
            case "invoice.paid" -> handleInvoicePaid(); // when invoice is paid
            case "invoice.payment_failed" -> handleInvoicePaymentFailed(); // when invoice is not paid , makr as PAST_DUE
            default -> log.debug("Ignoring the event {}:",type);
        }
    }
    private void handleCheckoutSessionCompleted(Session session) {

    }

    private void handleCustomerSubscriptionUpdated() {
    }

    private void handleCustomerSubscriptionDeleted() {
    }

    private void handleInvoicePaid() {
    }

    private void handleInvoicePaymentFailed() {
    }






}
