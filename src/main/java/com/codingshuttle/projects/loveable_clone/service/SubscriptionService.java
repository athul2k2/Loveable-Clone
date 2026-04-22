package com.codingshuttle.projects.loveable_clone.service;

import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutRequest;
import com.codingshuttle.projects.loveable_clone.dto.subscription.CheckoutResponse;
import com.codingshuttle.projects.loveable_clone.dto.subscription.PortalResponse;
import com.codingshuttle.projects.loveable_clone.dto.subscription.SubscriptionResponse;
import com.codingshuttle.projects.loveable_clone.enums.SubscriptionStatus;
import org.jspecify.annotations.Nullable;

import java.time.Instant;

public interface SubscriptionService {

     SubscriptionResponse getCurrentSubscription(Long userId);

     void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId);

     void updateSubscription(String id, SubscriptionStatus status, Instant periodStart, Instant periodend, Boolean cancelAtPeriodEnd, Long planId);

     void cancelSubscription(String id);

     void renewSubscriptionPeriod(String subId, Instant periodStart, Instant periodEnd);

     void markSubscriptionPastDue(String subId);
}
