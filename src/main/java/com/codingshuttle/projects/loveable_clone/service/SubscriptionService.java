package com.codingshuttle.projects.loveable_clone.service;

import com.codingshuttle.projects.loveable_clone.dto.subscription.SubscriptionResponse;
import com.codingshuttle.projects.loveable_clone.enums.SubscriptionStatus;

import java.time.Instant;

public interface SubscriptionService {

     SubscriptionResponse getCurrentSubscription();

     void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId);

     void updateSubscription(String subscriptionId, SubscriptionStatus status, Instant periodStart, Instant periodend, Boolean cancelAtPeriodEnd, Long planId);

     void cancelSubscription(String subscriptionId);

     void renewSubscriptionPeriod(String subId, Instant periodStart, Instant periodEnd);

     void markSubscriptionPastDue(String subId);

     boolean canCreateNewProject();
}
