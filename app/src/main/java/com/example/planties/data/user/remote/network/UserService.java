package com.example.planties.data.user.remote.network;

import com.example.planties.core.Constant;
import com.example.planties.data.user.remote.dto.AdminRes;
import com.example.planties.data.user.remote.dto.UserDetailRes;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface UserService {
    @GET(Constant.PROFILE)
    Call<UserDetailRes> getProfile(@HeaderMap Map<String, String> token);

    @GET(Constant.ADMIN)
    Call<AdminRes> getAdmin(@HeaderMap Map<String, String> token);
}
