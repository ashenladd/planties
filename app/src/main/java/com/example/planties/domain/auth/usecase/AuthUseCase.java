package com.example.planties.domain.auth.usecase;

import android.content.Context;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.AuthResponse;
import com.example.planties.data.auth.repository.AuthRepositoryImpl;
import com.example.planties.domain.auth.repository.AuthRepository;

import javax.inject.Inject;

public class AuthUseCase {
    private final AuthRepository authRepository;

    @Inject
    public AuthUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void login(AuthRequest request, ResponseCallback<AuthResponse> responseCallback) {
        authRepository.login(request, responseCallback);
    }
}