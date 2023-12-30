package com.example.planties.data.leaderboards.remote.dto;

import com.google.gson.annotations.SerializedName;

public class LeaderboardsResModel {
    @SerializedName("user_id")
    public String userId;

    @SerializedName("rank")
    public int rank;

    @SerializedName("username")
    public String username;

    @SerializedName("oxygen")
    public int oxygen;
}
