package com.codingshuttle.projects.loveable_clone.controller;

import com.codingshuttle.projects.loveable_clone.dto.subscription.*;
import com.codingshuttle.projects.loveable_clone.service.PaymentProcesser;
import com.codingshuttle.projects.loveable_clone.service.PlanService;
import com.codingshuttle.projects.loveable_clone.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BillingController {

    private final PlanService planService;
    private final SubscriptionService subscriptionService;
    private final PaymentProcesser paymentProcesser;

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
}
