package com.example.planties.data.auth.remote.network;

import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.AuthResponse;
import com.example.planties.core.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST(Constant.AUTH)
    Call<AuthResponse> postLogin(@Body AuthRequest authRequest);
}
