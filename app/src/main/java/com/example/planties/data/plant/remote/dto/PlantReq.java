package com.example.planties.data.plant.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlantReq {
    @SerializedName("name")
    public String name;

    @SerializedName("banner")
    public String banner;

    @SerializedName("dob")
    public String dob;

    @SerializedName("imageBase64")
    public List<String> imageBase64;
}
