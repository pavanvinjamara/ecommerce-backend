package com.example.backend.service;

import com.example.backend.dto.SignupRequest;

public interface AuthService {
    void signup(SignupRequest request);
}
