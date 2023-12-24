package com.example.planties.data.auth.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.auth.remote.AuthRemoteDataSource;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.AuthResponse;
import com.example.planties.domain.auth.repository.AuthRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepositoryImpl implements AuthRepository {
    private final AuthRemoteDataSource authRemoteDataSource;
    private final TokenHandler tokenHandler;

    @Inject
    public AuthRepositoryImpl(AuthRemoteDataSource authRemoteDataSource, TokenHandler tokenHandler) {
        this.authRemoteDataSource = authRemoteDataSource;
        this.tokenHandler = tokenHandler;
    }


    @Override
    public void login(AuthRequest authRequest, ResponseCallback<AuthResponse> responseCallback) {
        authRemoteDataSource.postLogin(authRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    if (authResponse == null) {
                        responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Auth Failed", response.code()));
                        return;
                    }
                    String accessToken = authResponse.data.accessToken;
                    tokenHandler.saveAccessToken(accessToken);
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, authResponse, "success", response.code()));
                } else {
                    tokenHandler.clearAccessToken();
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Auth Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }
}
