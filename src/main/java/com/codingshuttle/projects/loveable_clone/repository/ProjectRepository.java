package com.codingshuttle.projects.loveable_clone.repository;

import com.codingshuttle.projects.loveable_clone.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long > {
}
