package com.example.planties.domain.oxygen_leaderboard.repository;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsDetailRes;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsListRes;

public interface LeaderboardsRepository {
    void getLeaderboards(ResponseCallback<LeaderboardsListRes> responseCallback);

    void getDetailLeaderboards(String userId, ResponseCallback<LeaderboardsDetailRes> responseCallback);
}
