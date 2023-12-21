package com.example.planties.data.auth.remote.dto;

import com.google.gson.annotations.SerializedName;

public class DataResAuthModel {
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("refresh_token")
    public String refreshToken;

    @SerializedName("role")
    public String role;
}
