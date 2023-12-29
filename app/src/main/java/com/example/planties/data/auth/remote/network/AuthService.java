package com.example.planties.data.auth.remote.network;

import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.LoginResponse;
import com.example.planties.core.Constant;
import com.example.planties.data.auth.remote.dto.RegisterRequest;
import com.example.planties.data.auth.remote.dto.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST(Constant.AUTH)
    Call<LoginResponse> postLogin(@Body AuthRequest authRequest);

    @POST(Constant.USER)
    Call<RegisterResponse> postRegister(@Body RegisterRequest registerRequestÏ);
}
