package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.member.InviteMemberRequest;
import com.codingshuttle.projects.loveable_clone.dto.member.MemberResponse;
import com.codingshuttle.projects.loveable_clone.dto.member.UpdateMemberRoleRequest;
import com.codingshuttle.projects.loveable_clone.entity.Project;
import com.codingshuttle.projects.loveable_clone.entity.ProjectMember;
import com.codingshuttle.projects.loveable_clone.entity.ProjectMemberId;
import com.codingshuttle.projects.loveable_clone.entity.User;
import com.codingshuttle.projects.loveable_clone.mapper.ProjectMemeberMapper;
import com.codingshuttle.projects.loveable_clone.repository.ProjectMemberRepository;
import com.codingshuttle.projects.loveable_clone.repository.ProjectRepository;
import com.codingshuttle.projects.loveable_clone.repository.UserRepository;
import com.codingshuttle.projects.loveable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;
    ProjectMemeberMapper projectMemeberMapper;
    UserRepository userRepository;

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
        Project project = getAccessibleProjectId(projectId, userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed ");
        }

        User invitee = userRepository.findByEmail(request.email()).orElseThrow();
        if(invitee.getId().equals(userId)){
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,invitee.getId());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Cannot invite once again");
        }

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();
       projectMemberRepository.save(member);

        return projectMemeberMapper.toProjectMemberResponseFromMember(member) ;
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, Long userId, UpdateMemberRoleRequest request) {
        Project project = getAccessibleProjectId(projectId, userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed ");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());

        projectMemberRepository.save(projectMember);

        return projectMemeberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long userId, Long memberId) {
        Project project = getAccessibleProjectId(projectId, userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed ");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Member not found in project");
        }

        projectMemberRepository.deleteById(projectMemberId);

    }

    //Internal function
    public Project getAccessibleProjectId(Long projectId, Long userId) {
        return projectRepository.findAccessableById(projectId, userId).orElseThrow();
    }
}
