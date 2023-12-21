package com.example.planties.data.plant.remote.dto;

import com.example.planties.data.garden.remote.dto.GardenResDataModel;
import com.google.gson.annotations.SerializedName;

public class PlantListRes {

    @SerializedName("data")
    public PlantResDataModel data;
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public String status;

}
