package com.example.planties.domain.auth.usecase;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.LoginResponse;
import com.example.planties.data.auth.remote.dto.RegisterRequest;
import com.example.planties.data.auth.remote.dto.RegisterResponse;
import com.example.planties.domain.auth.repository.AuthRepository;

import javax.inject.Inject;

public class AuthUseCase {
    private final AuthRepository authRepository;

    @Inject
    public AuthUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void login(AuthRequest request, ResponseCallback<LoginResponse> responseCallback) {
        authRepository.login(request, responseCallback);
    }

    public void register(RegisterRequest request, ResponseCallback<RegisterResponse> responseCallback) {
        authRepository.register(request, responseCallback);
    }

    public void logout() {
        authRepository.logout();
    }
}