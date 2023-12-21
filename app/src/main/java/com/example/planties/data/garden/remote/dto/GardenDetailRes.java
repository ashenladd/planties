
package com.example.planties.data.garden.remote.dto;

import com.google.gson.annotations.SerializedName;


public class GardenDetailRes {

    @SerializedName("data")
    private GardenResModel mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;

    public GardenResModel getData() {
        return mData;
    }

    public void setData(GardenResModel data) {
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
