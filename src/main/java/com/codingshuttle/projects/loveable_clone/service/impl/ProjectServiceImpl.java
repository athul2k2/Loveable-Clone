package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.project.ProjectRequest;
import com.codingshuttle.projects.loveable_clone.dto.project.ProjectResponse;
import com.codingshuttle.projects.loveable_clone.dto.project.ProjectSummaryResponse;
import com.codingshuttle.projects.loveable_clone.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {
    @Override
    public List<ProjectSummaryResponse> getUserProject(Long userId) {
        return List.of();
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public void softDelete(Long id, Long userId) {

    }
}
