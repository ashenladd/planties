package com.example.planties.data.plant.remote.dto;

import com.google.gson.annotations.SerializedName;

public class PlantListRes {

    @SerializedName("data")
    public PlantResListDataModel data;
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public String status;

}
