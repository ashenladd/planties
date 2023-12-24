package com.example.planties.data.user.remote;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.core.utils.NetworkUtil;
import com.example.planties.data.user.remote.dto.UserDetailRes;
import com.example.planties.data.user.remote.network.UserService;

import javax.inject.Inject;

import retrofit2.Call;

public class UserRemoteDataSourceImpl implements UserRemoteDataSource {
    private final UserService userService;
    private final TokenHandler tokenHandler;

    @Inject
    public UserRemoteDataSourceImpl(UserService userService, TokenHandler tokenHandler) {
        this.userService = userService;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public Call<UserDetailRes> getProfile() {
        return userService.getProfile(NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }
}
