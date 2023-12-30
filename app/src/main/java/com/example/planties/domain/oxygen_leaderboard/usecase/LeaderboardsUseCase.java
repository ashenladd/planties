package com.example.planties.domain.oxygen_leaderboard.usecase;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsDetailRes;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsListRes;
import com.example.planties.domain.oxygen_leaderboard.repository.LeaderboardsRepository;

import javax.inject.Inject;

public class LeaderboardsUseCase {
    private final LeaderboardsRepository leaderboardsRepository;

    @Inject
    public LeaderboardsUseCase(LeaderboardsRepository leaderboardsRepository) {
        this.leaderboardsRepository = leaderboardsRepository;
    }

    public void getLeaderboards(ResponseCallback<LeaderboardsListRes> responseCallback) {
        leaderboardsRepository.getLeaderboards(responseCallback);
    }

    public void getDetailLeaderboards(String userId, ResponseCallback<LeaderboardsDetailRes> responseCallback) {
        leaderboardsRepository.getDetailLeaderboards(userId, responseCallback);
    }
}
