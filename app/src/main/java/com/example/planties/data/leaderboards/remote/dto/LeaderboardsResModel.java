package com.example.planties.data.leaderboards.remote.dto;

import com.google.gson.annotations.SerializedName;

public class LeaderboardsResModel {
    @SerializedName("user_id")
    private String userId;

    @SerializedName("rank")
    private int rank;

    @SerializedName("username")
    private String username;

    @SerializedName("oxygen")
    private float oxygen;


    public String getUserId() {
        return userId;
    }

    public int getRank() {
        return rank;
    }

    public String getUsername() {
        return username;
    }

    public double getOxygen() {
        return oxygen;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }
}
