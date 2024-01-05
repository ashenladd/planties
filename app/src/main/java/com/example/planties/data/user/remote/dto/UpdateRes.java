package com.example.planties.data.user.remote.dto;

import com.google.gson.annotations.SerializedName;

public class UpdateRes {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
