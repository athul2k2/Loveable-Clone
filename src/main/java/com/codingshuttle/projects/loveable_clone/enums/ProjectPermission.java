package com.codingshuttle.projects.loveable_clone.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectPermission {
    VIEW("project:view"),
    EDIT("project:edit"),
    DELETE("project:delete"),

    MANAGE_MEMBERS("project:manage_members"),
    VIEW_MEMBERS("project_members:view");

    private final String value;
}
