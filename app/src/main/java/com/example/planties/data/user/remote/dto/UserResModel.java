package com.example.planties.data.user.remote.dto;

import com.google.gson.annotations.SerializedName;

public class UserResModel {
    @SerializedName("full_name")
    public String fullName;

    @SerializedName("role")
    public String role;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("url_image")
    public String urlImage;
}
