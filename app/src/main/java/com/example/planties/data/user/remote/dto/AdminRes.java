package com.example.planties.data.user.remote.dto;

import com.google.gson.annotations.SerializedName;

public class AdminRes {
    @SerializedName("data")
    public AdminResDataModel data;

    @SerializedName("message")
    public String message;

    @SerializedName("status")
    public String status;
}
