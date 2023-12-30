package com.example.planties.data.leaderboards.remote.dto;

import com.google.gson.annotations.SerializedName;

public class LeaderboardsListRes {
    @SerializedName("data")
    public LeaderboardsListDetailModel data;

    @SerializedName("message")
    public String message;

    @SerializedName("status")
    public String status;
}
