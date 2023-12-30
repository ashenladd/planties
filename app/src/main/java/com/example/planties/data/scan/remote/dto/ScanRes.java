package com.example.planties.data.scan.remote.dto;

import com.example.planties.data.user.remote.dto.UserResDetailDataModel;
import com.google.gson.annotations.SerializedName;

public class ScanRes {

    @SerializedName("confidence")
    public String confidence;

    @SerializedName("prediction")
    public String prediction;
}
