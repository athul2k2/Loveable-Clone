package com.codingshuttle.projects.loveable_clone.dto.member;

import com.codingshuttle.projects.loveable_clone.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String email,
        String name,
        ProjectRole projectRole,
        Instant invitedAt
) {
}
