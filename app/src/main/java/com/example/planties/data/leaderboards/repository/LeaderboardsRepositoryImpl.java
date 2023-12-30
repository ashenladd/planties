package com.example.planties.data.leaderboards.repository;

import androidx.annotation.NonNull;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.leaderboards.remote.LeaderboardsRemoteDataSource;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsDetailRes;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsListRes;
import com.example.planties.domain.oxygen_leaderboard.repository.LeaderboardsRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardsRepositoryImpl implements LeaderboardsRepository {
    private final LeaderboardsRemoteDataSource leaderboardsDataSource;

    public LeaderboardsRepositoryImpl(LeaderboardsRemoteDataSource leaderboardsDataSource) {
        this.leaderboardsDataSource = leaderboardsDataSource;
    }
    @Override
    public void getLeaderboards(ResponseCallback<LeaderboardsListRes> responseCallback) {
        leaderboardsDataSource.getLeaderboardsList().enqueue(new Callback<LeaderboardsListRes>() {
            @Override
            public void onResponse(@NonNull Call<LeaderboardsListRes> call, @NonNull Response<LeaderboardsListRes> response) {
                if (response.isSuccessful()) {
                    LeaderboardsListRes leaderboardsListRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS,leaderboardsListRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LeaderboardsListRes> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void getDetailLeaderboards(String userId, ResponseCallback<LeaderboardsDetailRes> responseCallback) {
        leaderboardsDataSource.getDetailLeaderboards(userId).enqueue(new Callback<LeaderboardsDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<LeaderboardsDetailRes> call, @NonNull Response<LeaderboardsDetailRes> response) {
                if (response.isSuccessful()) {
                    LeaderboardsDetailRes leaderboardsDetailRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, leaderboardsDetailRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LeaderboardsDetailRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });

    }
}
