package com.example.planties.data.plant.remote.dto;

import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlantResDataModel {
    @SerializedName("plants")
    private List<PlantResModel> mPlants;

    public List<PlantResModel> getPlant() {
        return mPlants;
    }

    public void setPlants(List<PlantResModel> plants) {
        mPlants = plants;
    }

}
