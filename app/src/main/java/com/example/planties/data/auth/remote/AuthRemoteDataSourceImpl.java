package com.example.planties.data.auth.remote;

import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.LoginResponse;
import com.example.planties.data.auth.remote.dto.RegisterRequest;
import com.example.planties.data.auth.remote.dto.RegisterResponse;
import com.example.planties.data.auth.remote.network.AuthService;

import javax.inject.Inject;

import retrofit2.Call;

public class AuthRemoteDataSourceImpl implements AuthRemoteDataSource{
    private final AuthService authService;
    @Inject
    public AuthRemoteDataSourceImpl(AuthService authService){
        this.authService = authService;
    }
    @Override
    public Call<LoginResponse> postLogin(AuthRequest authRequest) {
        return authService.postLogin(authRequest);
    }

    @Override
    public Call<RegisterResponse> postRegister(RegisterRequest registerRequest) {
        return authService.postRegister(registerRequest);
    }
}
