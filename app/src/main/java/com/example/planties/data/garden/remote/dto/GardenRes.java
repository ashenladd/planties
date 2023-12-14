
package com.example.planties.data.garden.remote.dto;

import com.google.gson.annotations.SerializedName;

public class GardenRes {

    @SerializedName("data")
    private GardenResDataModel mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;

    public GardenResDataModel getData() {
        return mData;
    }

    public void setData(GardenResDataModel data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
