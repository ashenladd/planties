package com.example.planties.data.source.remote.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @Expose
    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public DataReq data;

}
