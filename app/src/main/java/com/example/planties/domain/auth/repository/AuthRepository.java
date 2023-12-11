package com.example.planties.domain.auth.repository;

import android.util.Log;

import com.example.planties.data.source.remote.dto.AuthRequest;
import com.example.planties.data.source.remote.dto.AuthResponse;
import com.example.planties.data.source.remote.network.AuthService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final AuthService authService;
    @Inject
    public AuthRepository(AuthService authService) {
        this.authService = authService;
    }

    public void login(AuthRequest authRequest) {
        authService.postLogin(authRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Log.d("onSuccess","username: "+authRequest.getUsername());
                Log.d("onSuccess","password: "+authRequest.getPassword());
                Log.d("onSuccess", String.valueOf(response.toString())); //getName() is the getter method that declared in the Book model class
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("error akif", t.toString());
            }
        });
    }
}
