package com.codingshuttle.projects.loveable_clone.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.codingshuttle.projects.loveable_clone.enums.ProjectPermission.*;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {
    EDITOR(VIEW,EDIT,VIEW_MEMBERS),
    VIEWER(VIEW,VIEW_MEMBERS),
    OWNER(VIEW,EDIT,DELETE,MANAGE_MEMBERS,VIEW_MEMBERS);

    ProjectRole(ProjectPermission... permissions){
        this.permissions = Set.of(permissions);
    }


    private final Set<ProjectPermission> permissions;
}
