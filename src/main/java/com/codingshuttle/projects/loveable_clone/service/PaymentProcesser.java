package com.codingshuttle.projects.loveable_clone.service;

import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutRequest;
import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutResponse;
import com.codingshuttle.projects.loveable_clone.dto.subscription.PortalResponse;
import com.stripe.model.StripeObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface PaymentProcesser {

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);

    PortalResponse openCustomerPortal(Long userId);

    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
