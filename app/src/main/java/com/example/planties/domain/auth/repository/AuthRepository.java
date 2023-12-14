package com.example.planties.domain.auth.repository;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.AuthResponse;

import retrofit2.Call;

public interface AuthRepository {
    void login(AuthRequest authRequest, ResponseCallback<AuthResponse> responseCallback);
}
