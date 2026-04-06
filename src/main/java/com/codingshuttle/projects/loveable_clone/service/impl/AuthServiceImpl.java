package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.auth.AuthResponse;
import com.codingshuttle.projects.loveable_clone.dto.auth.LoginRequest;
import com.codingshuttle.projects.loveable_clone.dto.auth.SignupRequest;
import com.codingshuttle.projects.loveable_clone.service.AuthService;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public @Nullable AuthResponse login(LoginRequest request) {
        return null;
    }
}
