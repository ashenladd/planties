package com.example.planties.data.auth.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.auth.remote.AuthRemoteDataSource;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.LoginResponse;
import com.example.planties.data.auth.remote.dto.RegisterRequest;
import com.example.planties.data.auth.remote.dto.RegisterResponse;
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
    public void login(AuthRequest authRequest, ResponseCallback<LoginResponse> responseCallback) {
        authRemoteDataSource.postLogin(authRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse == null) {
                        responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Auth Failed", response.code()));
                        return;
                    }
                    String accessToken = loginResponse.data.accessToken;
                    String refreshToken = loginResponse.data.refreshToken;
                    String role = loginResponse.data.role;
                    tokenHandler.saveAccessToken(accessToken);
                    tokenHandler.saveRefreshToken(refreshToken);
                    tokenHandler.saveRole(role);
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, loginResponse, "success", response.code()));
                } else {
                    tokenHandler.clearAccessToken();
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Auth Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void register(RegisterRequest registerRequest, ResponseCallback<RegisterResponse> responseCallback) {
        authRemoteDataSource.postRegister(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse == null) {
                        responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Auth Failed", response.code()));
                        return;
                    }
                    String accessToken = registerResponse.data.accessToken;
                    String refreshToken = registerResponse.data.refreshToken;
                    tokenHandler.saveAccessToken(accessToken);
                    tokenHandler.saveRefreshToken(refreshToken);
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, registerResponse, "success", response.code()));
                } else {
                    tokenHandler.clearAccessToken();
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Auth Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, Throwable t) {
                Log.d("debugLog", "Failed To Call Api");
            }
        });
    }

    @Override
    public void logout() {
        tokenHandler.clearAccessToken();
        tokenHandler.clearRole();
    }
}
