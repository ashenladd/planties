
package com.example.planties.data.garden.remote.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GardenResListDataModel {

    @SerializedName("gardens")
    private List<GardenResModel> mGardens;

    public List<GardenResModel> getGardens() {
        return mGardens;
    }

    public void setGardens(List<GardenResModel> gardens) {
        mGardens = gardens;
    }

}
