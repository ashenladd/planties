package com.example.planties.data.garden.remote.dto;

import com.google.gson.annotations.SerializedName;

public class GardenReq {
    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
