package com.example.planties.data.leaderboards.remote.dto;

import com.google.gson.annotations.SerializedName;

public class LeaderboardsDetailRes {
    @SerializedName("data")
    public LeaderboardsDetailDataModel data;

    @SerializedName("message")
    public String message;

    @SerializedName("status")
    public String status;
}
