package com.codingshuttle.projects.loveable_clone.security;

import com.codingshuttle.projects.loveable_clone.enums.ProjectPermission;
import com.codingshuttle.projects.loveable_clone.enums.ProjectRole;
import com.codingshuttle.projects.loveable_clone.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpression {

    private final ProjectMemberRepository projectMemberRepository;
    private final AuthUtil authUtil;

    public boolean canViewProject(Long projectId){
        Long userId = authUtil.getCurrentUserId();

        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId)
                .map(role -> role.getPermissions().contains(ProjectPermission.VIEW))
                .orElse(false);


    }

    public boolean canEditProject(Long projectId){
        Long userId = authUtil.getCurrentUserId();

        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId)
                .map(role -> role.getPermissions().contains(ProjectPermission.EDIT))
                .orElse(false);
    }
}
