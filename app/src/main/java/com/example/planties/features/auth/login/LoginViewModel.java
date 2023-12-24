package com.example.planties.features.auth.login;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.AuthResponse;
import com.example.planties.domain.auth.usecase.AuthUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private final AuthUseCase authUseCase;
    private final MutableLiveData<BaseResultResponse<AuthResponse>> authResponseLiveData = new MutableLiveData<>();
    public LiveData<BaseResultResponse<AuthResponse>> getAuthResponseLiveData() {
        return authResponseLiveData;
    }
    @Inject
    public LoginViewModel(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    public void processEvent(LoginViewEvent event){
        if(event instanceof LoginViewEvent.LoginButtonClicked){
            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername(((LoginViewEvent.LoginButtonClicked) event).getUsername());
            authRequest.setPassword(((LoginViewEvent.LoginButtonClicked) event).getPassword());
            login(authRequest);
        }
    }
    private void login(AuthRequest request) {
        authUseCase.login(request, new ResponseCallback<AuthResponse>() {
            @Override
            public void onSuccess(BaseResultResponse<AuthResponse> response) {
                authResponseLiveData.setValue(response);
            }

            @Override
            public void onFailure(BaseResultResponse<AuthResponse> response) {
                authResponseLiveData.setValue(response);
            }
        });
    }
}
