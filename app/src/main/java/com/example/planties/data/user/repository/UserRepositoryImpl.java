package com.example.planties.data.user.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.user.remote.UserRemoteDataSource;
import com.example.planties.data.user.remote.dto.AdminRes;
import com.example.planties.data.user.remote.dto.UserDetailRes;
import com.example.planties.data.user.remote.dto.UserResDetailDataModel;
import com.example.planties.domain.user.repository.UserRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    private final UserRemoteDataSource userRemoteDataSource;

    @Inject
    public UserRepositoryImpl(UserRemoteDataSource userRemoteDataSource) {
        this.userRemoteDataSource = userRemoteDataSource;
    }

    @Override
    public void getProfile(ResponseCallback<UserDetailRes> responseCallback) {
        userRemoteDataSource.getProfile().enqueue(new Callback<UserDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<UserDetailRes> call, @NonNull Response<UserDetailRes> response) {
                if (response.isSuccessful()) {
                    UserDetailRes userDetailRes = response.body();
                    if (userDetailRes == null) {
                        responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                        return;
                    }
                    Log.d("User:", "User Message Fullname=" + userDetailRes.data.user.fullName);
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, userDetailRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDetailRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void getAdmin(ResponseCallback<AdminRes> responseCallback) {
        userRemoteDataSource.getAdmin().enqueue(new Callback<AdminRes>() {
            @Override
            public void onResponse(@NonNull Call<AdminRes> call, @NonNull Response<AdminRes> response) {
                if (response.isSuccessful()) {
                    AdminRes adminRes = response.body();
                    if (adminRes == null) {
                        responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                        return;
                    }
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, adminRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<AdminRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }
}
