package com.example.planties.domain.auth.usecase;

import com.example.planties.data.source.remote.dto.AuthRequest;
import com.example.planties.domain.auth.repository.AuthRepository;

import javax.inject.Inject;

public class AuthUseCase {
    private final AuthRepository authRepository;

    @Inject
    public AuthUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void login(AuthRequest request) {
        authRepository.login(request);
    }
}
