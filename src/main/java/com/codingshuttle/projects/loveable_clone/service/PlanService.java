package com.codingshuttle.projects.loveable_clone.service;

import com.codingshuttle.projects.loveable_clone.dto.subscription.PlanResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface PlanService {
     List<PlanResponse> getAllActivePlans();
}
