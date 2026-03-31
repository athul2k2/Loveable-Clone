package com.codingshuttle.projects.loveable_clone.service;

import com.codingshuttle.projects.loveable_clone.dto.auth.AuthResponse;
import com.codingshuttle.projects.loveable_clone.dto.auth.LoginRequest;
import com.codingshuttle.projects.loveable_clone.dto.auth.SignupRequest;
import org.jspecify.annotations.Nullable;


public interface AuthService {

     AuthResponse signup(SignupRequest request);

     @Nullable AuthResponse login(LoginRequest request);
}
