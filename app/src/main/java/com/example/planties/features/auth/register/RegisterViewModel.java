package com.example.planties.features.auth.register;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.LoginResponse;
import com.example.planties.data.auth.remote.dto.RegisterRequest;
import com.example.planties.data.auth.remote.dto.RegisterResponse;
import com.example.planties.domain.auth.usecase.AuthUseCase;
import com.example.planties.features.auth.login.LoginViewEvent;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterViewModel extends ViewModel {
    private final AuthUseCase authUseCase;
    private final MutableLiveData<BaseResultResponse<RegisterResponse>> authResponseLiveData = new MutableLiveData<>();
    public MutableLiveData<BaseResultResponse<RegisterResponse>> getAuthResponseLiveData() {
        return authResponseLiveData;
    }

    @Inject
    public RegisterViewModel(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    public void processEvent(RegisterViewEvent event) {
        if (event instanceof RegisterViewEvent.RegisterButtonClicked) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setName(((RegisterViewEvent.RegisterButtonClicked) event).getName());
            registerRequest.setUsername(((RegisterViewEvent.RegisterButtonClicked) event).getUsername());
            registerRequest.setPassword(((RegisterViewEvent.RegisterButtonClicked) event).getPassword());
            register(registerRequest);
        }
    }

    private void register(RegisterRequest request) {
        authUseCase.register(request, new ResponseCallback<RegisterResponse>() {
            @Override
            public void onSuccess(BaseResultResponse<RegisterResponse> response) {
                authResponseLiveData.setValue(response);
            }

            @Override
            public void onFailure(BaseResultResponse<RegisterResponse> response) {
                authResponseLiveData.setValue(response);
            }
        });
    }
}
