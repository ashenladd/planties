package com.example.planties.data.auth.remote.dto;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public DataResAuthModel data;

    @SerializedName("message")
    public String message;
};

