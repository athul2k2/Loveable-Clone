package com.codingshuttle.projects.loveable_clone.mapper;

import com.codingshuttle.projects.loveable_clone.dto.member.MemberResponse;
import com.codingshuttle.projects.loveable_clone.entity.ProjectMember;
import com.codingshuttle.projects.loveable_clone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemeberMapper {


    @Mapping(target = "userId" ,source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner);

    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);
}
