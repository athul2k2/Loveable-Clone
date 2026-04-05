package com.codingshuttle.projects.loveable_clone.dto.subscription;

public record UsageTodayResponse(
        Integer tokensUsed,
        Integer tokenLimit,
        Integer previewsRunning,
        Integer previewsLimit
) {
}
