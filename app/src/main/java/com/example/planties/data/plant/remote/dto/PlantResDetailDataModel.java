package com.example.planties.data.plant.remote.dto;

import com.google.gson.annotations.SerializedName;

public class PlantResDetailDataModel {
    @SerializedName("plants")
    private PlantResModel mPlant;

    public PlantResModel getPlant() {
        return mPlant;
    }
    public void setPlant(PlantResModel plant) {
        mPlant = plant;
    }
}
