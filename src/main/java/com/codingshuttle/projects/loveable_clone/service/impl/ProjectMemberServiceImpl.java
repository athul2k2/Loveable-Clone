package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.member.InviteMemberRequest;
import com.codingshuttle.projects.loveable_clone.dto.member.MemberResponse;
import com.codingshuttle.projects.loveable_clone.dto.member.UpdateMemberRoleRequest;
import com.codingshuttle.projects.loveable_clone.service.ProjectMemberService;

import java.util.List;

public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, Long userid, UpdateMemberRoleRequest request) {
        return null;
    }

    @Override
    public MemberResponse deleteProjectMember(Long projectId, Long userId, Long memberId) {
        return null;
    }
}
