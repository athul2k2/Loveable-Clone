package com.codingshuttle.projects.loveable_clone.dto.subscription;

public record PlanResponse(
        Long id,
        String name,
        Integer maxProject,
        Integer maxTokensPerDay,
        Boolean unlimitedAi,
        String price
) {
}
