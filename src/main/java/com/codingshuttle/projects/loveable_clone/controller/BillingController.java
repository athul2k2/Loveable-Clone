package com.codingshuttle.projects.loveable_clone.controller;

import com.codingshuttle.projects.loveable_clone.dto.subscription.*;
import com.codingshuttle.projects.loveable_clone.service.PaymentProcesser;
import com.codingshuttle.projects.loveable_clone.service.PlanService;
import com.codingshuttle.projects.loveable_clone.service.SubscriptionService;
import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BillingController {

    private final PlanService planService;
    private final SubscriptionService subscriptionService;
    private final PaymentProcesser paymentProcesser;

    @Value("${stripe.webhooks.secret}")
    private String webHookSecret;

    @GetMapping("/api/plans")
    public ResponseEntity<List<PlanResponse>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllActivePlans());
    }

    @GetMapping("/api/me/subscription")
    public ResponseEntity<SubscriptionResponse> getMySubscription(){
        Long userId = 1L;
        return ResponseEntity.ok(subscriptionService.getCurrentSubscription(userId));
    }

    @PostMapping("/api/payments/checkout")
    public ResponseEntity<CheckoutResponse> createCheckoutResponse(
            @RequestBody CheckoutRequest request
    ){

        return ResponseEntity.ok(paymentProcesser.createCheckoutSessionUrl(request));
    }

    @PostMapping("/api/payments/portal")
    public ResponseEntity<PortalResponse> openCustomerPortal(){
        Long userId =1L;
        return ResponseEntity.ok(paymentProcesser.openCustomerPortal(userId));
    }

    @PostMapping("/webhooks/payment")
    public ResponseEntity<String> handlePaymentWebhooks(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader
    ){
        try {
            Event event = Webhook.constructEvent(payload,sigHeader, webHookSecret);

            EventDataObjectDeserializer deserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;

            if(deserializer.getObject().isPresent()){
                stripeObject = deserializer.getObject().get();
            }else{
                try {
                    stripeObject = deserializer.deserializeUnsafe();
                    if(stripeObject == null){
                        log.warn("Failed to deserialize webhook object for event:{}",event.getType());
                        return ResponseEntity.ok().build();
                    }

                } catch (EventDataObjectDeserializationException e) {
                    log.error("Unsafe deserialization failed for event {}:{}",event.getType(),e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deserialization failed");
                }
            }
            Map<String,String> metadata = new HashMap<>();
            if(stripeObject instanceof Session session){
                metadata = session.getMetadata();
            }

            paymentProcesser.handleWebhookEvent(event.getType(),stripeObject,metadata);
            return ResponseEntity.ok().build();


        } catch (SignatureVerificationException e) {
            throw new RuntimeException(e);
        }
    }
}
