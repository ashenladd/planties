package com.example.planties.domain.user.repository;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.user.remote.dto.AdminRes;
import com.example.planties.data.user.remote.dto.UserDetailRes;
import com.example.planties.data.user.remote.dto.UserResDetailDataModel;

public interface UserRepository {
    void getProfile(ResponseCallback<UserDetailRes> responseCallback);
    void getAdmin(ResponseCallback<AdminRes> responseCallback);
}
