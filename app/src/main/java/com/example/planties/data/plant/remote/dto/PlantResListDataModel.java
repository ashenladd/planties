package com.example.planties.data.plant.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlantResListDataModel {
    @SerializedName("plants")
    private List<PlantResModel> mPlants;

    public List<PlantResModel> getPlants() {
        return mPlants;
    }

    public void setPlants(List<PlantResModel> plants) {
        mPlants = plants;
    }

}
