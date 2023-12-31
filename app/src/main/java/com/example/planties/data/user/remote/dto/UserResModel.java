package com.example.planties.data.user.remote.dto;

import com.google.gson.annotations.SerializedName;

public class UserResModel {
    @SerializedName("id")
    public String userId;

    @SerializedName("full_name")
    public String fullName;

    @SerializedName("role")
    public String role;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("total_garden")
    public int total_garden;


    @SerializedName("rank")
    public int rank;

    @SerializedName("total_plant")
    public int total_plant;

    @SerializedName("url_image")
    public String urlImage;
}
