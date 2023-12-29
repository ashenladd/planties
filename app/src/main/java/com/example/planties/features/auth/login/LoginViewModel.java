package com.example.planties.features.auth.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.LoginResponse;
import com.example.planties.domain.auth.usecase.AuthUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private final AuthUseCase authUseCase;
    private final MutableLiveData<BaseResultResponse<LoginResponse>> authResponseLiveData = new MutableLiveData<>();
    public LiveData<BaseResultResponse<LoginResponse>> getAuthResponseLiveData() {
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
        authUseCase.login(request, new ResponseCallback<LoginResponse>() {
            @Override
            public void onSuccess(BaseResultResponse<LoginResponse> response) {
                authResponseLiveData.setValue(response);
            }

            @Override
            public void onFailure(BaseResultResponse<LoginResponse> response) {
                authResponseLiveData.setValue(response);
            }
        });
    }
}
