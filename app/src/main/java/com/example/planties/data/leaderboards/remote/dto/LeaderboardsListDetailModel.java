package com.example.planties.data.leaderboards.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaderboardsListDetailModel {
    @SerializedName("leaderboards")
    private List<LeaderboardsResModel> leaderboards;

    public List<LeaderboardsResModel> getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(List<LeaderboardsResModel> leaderboards) {
        this.leaderboards = leaderboards;
    }
}
