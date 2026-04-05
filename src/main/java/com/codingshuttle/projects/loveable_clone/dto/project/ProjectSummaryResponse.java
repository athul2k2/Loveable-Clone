package com.codingshuttle.projects.loveable_clone.dto.project;

import java.time.Instant;

public record ProjectSummaryResponse(
        Long id,
        String name,
        String desc,
        Instant createdAt,
        Instant updatedAt
) {
}
