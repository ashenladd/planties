package com.example.planties.data.auth.remote.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public DataResAuthModel data;

    @SerializedName("message")
    public String message;

}
