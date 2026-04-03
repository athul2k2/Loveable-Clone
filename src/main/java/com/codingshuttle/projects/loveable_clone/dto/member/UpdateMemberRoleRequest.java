package com.codingshuttle.projects.loveable_clone.dto.member;

import com.codingshuttle.projects.loveable_clone.enums.ProjectRole;

public record UpdateMemberRoleRequest(
        ProjectRole role
) {
}
