package com.codingshuttle.projects.loveable_clone.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
