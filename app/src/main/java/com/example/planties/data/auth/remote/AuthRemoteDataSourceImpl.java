package com.example.planties.data.auth.remote;

import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.data.auth.remote.dto.AuthResponse;
import com.example.planties.data.auth.remote.network.AuthService;

import retrofit2.Call;

public class AuthRemoteDataSourceImpl implements AuthRemoteDataSource{
    private final AuthService authService;
    public AuthRemoteDataSourceImpl(AuthService authService){
        this.authService = authService;
    }
    @Override
    public Call<AuthResponse> postLogin(AuthRequest authRequest) {
        return authService.postLogin(authRequest);
    }
}
