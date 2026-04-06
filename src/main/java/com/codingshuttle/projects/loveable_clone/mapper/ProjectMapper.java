package com.codingshuttle.projects.loveable_clone.mapper;

import com.codingshuttle.projects.loveable_clone.dto.project.ProjectResponse;
import com.codingshuttle.projects.loveable_clone.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);
}
