package com.example.planties.data.leaderboards.remote;

import android.util.Log;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.core.utils.NetworkUtil;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsDetailRes;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsListRes;
import com.example.planties.data.leaderboards.remote.network.LeaderboardsService;

import retrofit2.Call;

public class LeaderboardsRemoteDataSourceImpl implements LeaderboardsRemoteDataSource {
    private final LeaderboardsService leaderboardsService;
    private final TokenHandler tokenHandler;

    public LeaderboardsRemoteDataSourceImpl(LeaderboardsService leaderboardsService, TokenHandler tokenHandler) {
        this.leaderboardsService = leaderboardsService;
        this.tokenHandler = tokenHandler;
    }
    @Override
    public Call<LeaderboardsListRes> getLeaderboardsList() {
        return leaderboardsService.getLeaderboardsList(NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }

    @Override
    public Call<LeaderboardsDetailRes> getDetailLeaderboards() {
        Log.d("LeaderboardsRemoteDataSourceImpl", "getDetailLeaderboards: " + NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
        return leaderboardsService.getDetailLeaderboards(NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }
}
