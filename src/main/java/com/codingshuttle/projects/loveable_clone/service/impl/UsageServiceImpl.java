package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.subscription.PlanLimitsResponse;
import com.codingshuttle.projects.loveable_clone.dto.subscription.UsageTodayResponse;
import com.codingshuttle.projects.loveable_clone.service.UsageService;

public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
