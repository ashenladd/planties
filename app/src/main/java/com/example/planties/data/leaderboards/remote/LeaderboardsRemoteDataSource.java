package com.example.planties.data.leaderboards.remote;

import com.example.planties.data.leaderboards.remote.dto.LeaderboardsDetailRes;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsListRes;

import retrofit2.Call;

public interface LeaderboardsRemoteDataSource {
    Call<LeaderboardsListRes> getLeaderboardsList();
    Call<LeaderboardsDetailRes> getDetailLeaderboards(String userId);

}
