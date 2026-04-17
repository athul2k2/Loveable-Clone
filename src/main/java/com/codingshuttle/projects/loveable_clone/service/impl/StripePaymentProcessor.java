package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutRequest;
import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutResponse;
import com.codingshuttle.projects.loveable_clone.dto.subscription.PortalResponse;
import com.codingshuttle.projects.loveable_clone.entity.Plan;
import com.codingshuttle.projects.loveable_clone.error.ResourceNotFoundException;
import com.codingshuttle.projects.loveable_clone.repository.PlanRepository;
import com.codingshuttle.projects.loveable_clone.security.AuthUtil;
import com.codingshuttle.projects.loveable_clone.service.PaymentProcesser;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StripePaymentProcessor implements PaymentProcesser {

    @Value("${client.url}")
    private String fontendUrl;

    private final AuthUtil authUtil;
    private final PlanRepository planRepository;

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Plan plan = planRepository.findById(request.planId()).orElseThrow(() ->
                new ResourceNotFoundException("Plan", request.planId().toString()));
        SessionCreateParams params = SessionCreateParams.builder()
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
                .putMetadata("plan_id",plan.getId().toString())
                .build();
        try {
            Session session = Session.create(params);
            return new CheckoutResponse(session.getUrl());

        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
