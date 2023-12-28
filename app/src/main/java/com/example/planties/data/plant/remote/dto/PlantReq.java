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

    @SerializedName("type")
    public String type;

    @SerializedName("imageBase64")
    public List<String> imageBase64;

    public PlantReq(String name, String banner, String dob, String type, List<String> imageBase64) {
        this.name = name;
        this.banner = banner;
        this.dob = dob;
        this.type = type;
        this.imageBase64 = imageBase64;
    }
}
