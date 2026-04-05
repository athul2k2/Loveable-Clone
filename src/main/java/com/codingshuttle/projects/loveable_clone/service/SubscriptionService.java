package com.codingshuttle.projects.loveable_clone.service;

import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutRequest;
import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutResponse;
import com.codingshuttle.projects.loveable_clone.dto.subscription.PortalResponse;
import com.codingshuttle.projects.loveable_clone.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {
     SubscriptionResponse getCurrentSubscription(Long userId);

     CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

     PortalResponse openCustomerPortal(Long userId);
}
