package com.codingshuttle.projects.loveable_clone.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user)
{ }
