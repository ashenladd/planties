
package com.example.planties.data.garden.remote.dto;

import com.google.gson.annotations.SerializedName;

public class GardenResDetailDataModel {
    @SerializedName("gardens")
    private GardenResModel mGarden;

    public GardenResModel getGarden() {
        return mGarden;
    }

    public void setGarden(GardenResModel garden) {
        mGarden = garden;
    }

}
