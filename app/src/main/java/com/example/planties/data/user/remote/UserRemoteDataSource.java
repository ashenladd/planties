package com.example.planties.data.user.remote;

import com.example.planties.data.user.remote.dto.AdminRes;
import com.example.planties.data.user.remote.dto.UpdateRes;
import com.example.planties.data.user.remote.dto.UserDetailRes;

import retrofit2.Call;

public interface UserRemoteDataSource {
    Call<UserDetailRes> getProfile();
    Call<AdminRes> getAdmin();

    Call<UpdateRes> updateLeaderboard();
}
