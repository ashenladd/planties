package com.example.planties.data.leaderboards.remote.network;

import com.example.planties.core.Constant;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsDetailRes;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsListRes;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

public interface LeaderboardsService {
    @GET(Constant.LEADERBOARDS)
    Call<LeaderboardsListRes> getLeaderboardsList(@HeaderMap Map<String, String> token);

    @GET(Constant.DETAIL_LEADERBOARD)
    Call<LeaderboardsDetailRes> getDetailLeaderboards(@Path("userId") String userId, @HeaderMap Map<String, String> token);
}
