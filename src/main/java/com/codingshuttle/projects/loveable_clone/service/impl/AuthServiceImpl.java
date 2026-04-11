package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.auth.AuthResponse;
import com.codingshuttle.projects.loveable_clone.dto.auth.LoginRequest;
import com.codingshuttle.projects.loveable_clone.dto.auth.SignupRequest;
import com.codingshuttle.projects.loveable_clone.entity.User;
import com.codingshuttle.projects.loveable_clone.error.BadRequestException;
import com.codingshuttle.projects.loveable_clone.mapper.UserMapper;
import com.codingshuttle.projects.loveable_clone.repository.UserRepository;
import com.codingshuttle.projects.loveable_clone.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User already exists with username " + request.username());
        });

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user = userRepository.save(user);

        return new AuthResponse("abc-ski",userMapper.toUserProfileResponse(user));
    }

    @Override
    public @Nullable AuthResponse login(LoginRequest request) {
        return null;
    }
}
