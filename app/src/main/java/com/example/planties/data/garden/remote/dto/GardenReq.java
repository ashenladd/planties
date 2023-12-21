package com.example.planties.data.garden.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GardenReq {
    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    @SerializedName("imageBase64")
    public List<String> imageBase64;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
