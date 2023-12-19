package com.example.planties.data.auth.repository;

import android.content.Context;
import android.util.Log;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.AuthResponse;
import com.example.planties.data.auth.remote.network.AuthService;
import com.example.planties.domain.auth.repository.AuthRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService authService;
    @Inject
    public AuthRepositoryImpl(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public void login(AuthRequest authRequest, Context context, ResponseCallback<AuthResponse> responseCallback) {
        authService.postLogin(authRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    String accessToken = authResponse.data.accessToken;
                    TokenHandler.saveAccessToken(context, accessToken);
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, authResponse, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Auth Failed", response.code()));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }
}
