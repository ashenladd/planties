package com.example.planties.domain.user.usecase;

import android.util.Log;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.user.remote.dto.AdminRes;
import com.example.planties.data.user.remote.dto.AdminResModel;
import com.example.planties.data.user.remote.dto.UpdateRes;
import com.example.planties.data.user.remote.dto.UserDetailRes;
import com.example.planties.domain.user.repository.UserRepository;

import javax.inject.Inject;

public class UserUseCase {
    private final UserRepository userRepository;
    @Inject
    public UserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getProfile(ResponseCallback<UserDetailRes> responseCallback) {
        userRepository.getProfile(new ResponseCallback<UserDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<UserDetailRes> response) {
                Log.d("User:", "User Message=" + response.getData().data.user.fullName);
                responseCallback.onSuccess(new BaseResultResponse<UserDetailRes>(StatusResult.SUCCESS,response.getData(),response.getData().message,response.getCode()));
            }

            @Override
            public void onFailure(BaseResultResponse<UserDetailRes> response) {
                responseCallback.onFailure(response);
            }

        });
    }

    public void getAdmin(ResponseCallback<AdminRes> responseCallback){
        userRepository.getAdmin(new ResponseCallback<AdminRes>() {
            @Override
            public void onSuccess(BaseResultResponse<AdminRes> response) {
                responseCallback.onSuccess(new BaseResultResponse<AdminRes>(StatusResult.SUCCESS,response.getData(),response.getData().message,response.getCode()));
            }

            @Override
            public void onFailure(BaseResultResponse<AdminRes> response) {
                responseCallback.onFailure(response);
            }
        });
    }

    public void updateLeaderboard(ResponseCallback<UpdateRes> responseCallback){
        userRepository.updateLeaderboard(new ResponseCallback<UpdateRes>() {
            @Override
            public void onSuccess(BaseResultResponse<UpdateRes> response) {
                responseCallback.onSuccess(new BaseResultResponse<UpdateRes>(StatusResult.SUCCESS,response.getData(),response.getData().getMessage(),response.getCode()));
            }

            @Override
            public void onFailure(BaseResultResponse<UpdateRes> response) {
                responseCallback.onFailure(response);
            }
        });
    }
}
