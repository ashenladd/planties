package com.example.planties.data.plant.remote.dto;

import com.google.gson.annotations.SerializedName;

public class PlantDetailRes {
    @SerializedName("data")
    public PlantResDetailDataModel data;
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public String status;
}
