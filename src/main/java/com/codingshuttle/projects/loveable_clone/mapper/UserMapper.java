package com.codingshuttle.projects.loveable_clone.mapper;

import com.codingshuttle.projects.loveable_clone.dto.auth.SignupRequest;
import com.codingshuttle.projects.loveable_clone.dto.auth.UserProfileResponse;
import com.codingshuttle.projects.loveable_clone.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest );

    UserProfileResponse toUserProfileResponse(User user);
}
