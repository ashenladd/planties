package com.example.planties.data.user.remote.dto;

import com.google.gson.annotations.SerializedName;

public class UserDetailRes {
    @SerializedName("data")
    public UserResDetailDataModel data;

    @SerializedName("message")
    public String message;

    @SerializedName("status")
    public String status;
}
