package com.example.planties.data.auth.remote;

import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;

public interface AuthRemoteDataSource {
    Call<AuthResponse> postLogin(@Body AuthRequest authRequest);
}
