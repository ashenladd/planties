package com.example.planties.features.auth.login;

import androidx.lifecycle.ViewModel;

import com.example.planties.data.source.remote.dto.AuthRequest;
import com.example.planties.domain.auth.usecase.AuthUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private final AuthUseCase authUseCase;

    @Inject
    public LoginViewModel(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    public void login(AuthRequest request) {
        authUseCase.login(request);
    }
}
