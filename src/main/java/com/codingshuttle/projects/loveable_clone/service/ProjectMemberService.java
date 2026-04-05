package com.codingshuttle.projects.loveable_clone.service;

import com.codingshuttle.projects.loveable_clone.dto.member.InviteMemberRequest;
import com.codingshuttle.projects.loveable_clone.dto.member.MemberResponse;
import com.codingshuttle.projects.loveable_clone.entity.ProjectMember;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMember> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, Long userid, InviteMemberRequest request);

    MemberResponse deleteProjectMember(Long projectId, Long userId,Long memberId);
}
