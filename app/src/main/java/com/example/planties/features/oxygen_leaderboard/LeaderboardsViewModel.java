package com.example.planties.features.oxygen_leaderboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsDetailRes;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsListRes;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsResModel;
import com.example.planties.data.user.remote.dto.UserDetailRes;
import com.example.planties.data.user.remote.dto.UserResModel;
import com.example.planties.domain.oxygen_leaderboard.usecase.LeaderboardsUseCase;
import com.example.planties.domain.user.usecase.UserUseCase;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LeaderboardsViewModel extends ViewModel {
    private final LeaderboardsUseCase leaderboardsUseCase;

    @Inject
    public LeaderboardsViewModel(LeaderboardsUseCase leaderboardsUseCase) {
        this.leaderboardsUseCase = leaderboardsUseCase;

        getLeaderboardsData();
        getUserLeaderboardData();
    }

    private final MutableLiveData<List<LeaderboardsResModel>> leaderboards = new MutableLiveData<>();
    public LiveData<List<LeaderboardsResModel>> getLeaderboards() {
        return leaderboards;
    }
    private final MutableLiveData<LeaderboardsResModel> userLeaderboard = new MutableLiveData<>();
    public LiveData<LeaderboardsResModel> getUserLeaderboard() {
        return userLeaderboard;
    }

    public void processEvent(LeaderboardsViewEvent event) {
        if (event instanceof LeaderboardsViewEvent.OnRefresh) {
            getLeaderboardsData();
            getUserLeaderboardData();
        }
    }
    private void getLeaderboardsData() {
        leaderboardsUseCase.getLeaderboards(new ResponseCallback<LeaderboardsListRes>() {
            @Override
            public void onSuccess(BaseResultResponse<LeaderboardsListRes> response) {
                leaderboards.setValue(response.getData().data.getLeaderboards());
            }

            @Override
            public void onFailure(BaseResultResponse<LeaderboardsListRes> response) {

            }
        });
    }

    public void getUserLeaderboardData() {
        leaderboardsUseCase.getDetailLeaderboards(new ResponseCallback<LeaderboardsDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<LeaderboardsDetailRes> response) {
                userLeaderboard.setValue(response.getData().data.getLeaderboards());
            }

            @Override
            public void onFailure(BaseResultResponse<LeaderboardsDetailRes> response) {

            }
        });
    }
}
