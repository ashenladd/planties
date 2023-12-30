package com.example.planties.data.leaderboards.remote.dto;

import com.google.gson.annotations.SerializedName;

public class LeaderboardsDetailDataModel {
    @SerializedName("leaderboards")
    private LeaderboardsResModel leaderboards;

    public LeaderboardsResModel getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(LeaderboardsResModel leaderboards) {
        this.leaderboards = leaderboards;
    }
}
