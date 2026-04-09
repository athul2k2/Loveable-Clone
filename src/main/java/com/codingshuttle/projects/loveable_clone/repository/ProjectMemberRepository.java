package com.codingshuttle.projects.loveable_clone.repository;

import com.codingshuttle.projects.loveable_clone.entity.ProjectMember;
import com.codingshuttle.projects.loveable_clone.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);
}
