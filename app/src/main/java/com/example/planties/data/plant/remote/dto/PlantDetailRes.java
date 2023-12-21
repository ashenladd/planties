package com.example.planties.data.plant.remote.dto;

import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.google.gson.annotations.SerializedName;

public class PlantDetailRes {
    @SerializedName("data")
    public PlantResModel data;
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public String status;
}
