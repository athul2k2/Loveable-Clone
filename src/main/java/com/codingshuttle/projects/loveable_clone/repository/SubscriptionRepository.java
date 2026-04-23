package com.codingshuttle.projects.loveable_clone.repository;

import com.codingshuttle.projects.loveable_clone.entity.Subscription;
import com.codingshuttle.projects.loveable_clone.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    /*
    * Get the current active Subscription
     */

    Optional<Subscription> findByUserIdAndStatusIn(Long userId, Set<SubscriptionStatus> statusSet);

    boolean existsByStripeSbuscriptionId(String subscriptionId);
}
