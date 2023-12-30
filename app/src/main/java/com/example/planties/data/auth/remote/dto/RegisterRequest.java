package com.example.planties.data.auth.remote.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class RegisterRequest {
    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("fullname")
    private String fullname;

    @SerializedName("password")
    private String password;

    @SerializedName("profile_image")
    @Nullable
    private String profile_image;

    public void setName(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setProfile_image(String image) {
        this.profile_image = image;
    }
}
