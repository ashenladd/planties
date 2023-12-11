package com.example.planties.data.source.remote.network;

import com.example.planties.data.source.remote.dto.AuthRequest;
import com.example.planties.data.source.remote.dto.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/authentications")
    Call<AuthResponse> postLogin(@Body AuthRequest authRequest);
}
