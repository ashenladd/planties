package com.example.planties.data.auth.remote;

import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.LoginResponse;
import com.example.planties.data.auth.remote.dto.RegisterRequest;
import com.example.planties.data.auth.remote.dto.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;

public interface AuthRemoteDataSource {
    Call<LoginResponse> postLogin(@Body AuthRequest authRequest);

    Call<RegisterResponse> postRegister(@Body RegisterRequest registerRequest);
}
