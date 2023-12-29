package com.example.planties.domain.auth.repository;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.LoginResponse;
import com.example.planties.data.auth.remote.dto.RegisterRequest;
import com.example.planties.data.auth.remote.dto.RegisterResponse;

public interface AuthRepository {
    void login(AuthRequest authRequest, ResponseCallback<LoginResponse> responseCallback);
    void register(RegisterRequest registerRequest, ResponseCallback<RegisterResponse> responseCallback);
}
