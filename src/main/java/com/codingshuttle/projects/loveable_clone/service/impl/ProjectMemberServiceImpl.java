package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.member.InviteMemberRequest;
import com.codingshuttle.projects.loveable_clone.dto.member.MemberResponse;
import com.codingshuttle.projects.loveable_clone.dto.member.UpdateMemberRoleRequest;
import com.codingshuttle.projects.loveable_clone.entity.Project;
import com.codingshuttle.projects.loveable_clone.mapper.ProjectMemeberMapper;
import com.codingshuttle.projects.loveable_clone.repository.ProjectMemberRepository;
import com.codingshuttle.projects.loveable_clone.repository.ProjectRepository;
import com.codingshuttle.projects.loveable_clone.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;
    ProjectMemeberMapper projectMemeberMapper;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectId(projectId, userId);

        List<MemberResponse> memberResponseList = new ArrayList<>();
        memberResponseList.add(projectMemeberMapper.toProjectMemberResponseFromOwner(project.getOwner()));

        memberResponseList.addAll(
                projectMemberRepository.findByIdProjectId(projectId).stream()
                        .map(projectMemeberMapper::toProjectMemberResponseFromMember)
                        .toList()
        );
        return memberResponseList;
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

    //Internal function
    public Project getAccessibleProjectId(Long projectId, Long userId) {
        return projectRepository.findAccessableById(projectId, userId).orElseThrow();
    }
}
