package com.codingshuttle.projects.loveable_clone.dto.subscription;

public record UsageTodayResponse(
        int tokensUsed,
        int tokenLimit,
        int previewsRunning,
        int previewsLimit
) {
}
