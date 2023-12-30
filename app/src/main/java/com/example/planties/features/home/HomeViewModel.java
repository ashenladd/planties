package com.example.planties.features.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsDetailRes;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsResModel;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.plant.remote.dto.PlantResListDataModel;
import com.example.planties.data.user.remote.dto.UserDetailRes;
import com.example.planties.domain.garden.model.GardenModel;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.domain.oxygen_leaderboard.usecase.LeaderboardsUseCase;
import com.example.planties.domain.plant.usecase.PlantUseCase;
import com.example.planties.domain.user.usecase.UserUseCase;
import com.example.planties.features.utils.adapter.filter.FilterModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final GardenUseCase gardenUseCase;
    private final PlantUseCase plantUseCase;
    private final UserUseCase userUseCase;
    private final LeaderboardsUseCase leaderboardsUseCase;
    private final MutableLiveData<UserDetailRes> user = new MutableLiveData<>();
    private final MutableLiveData<PlantResListDataModel> plantList = new MutableLiveData<>();
    private final MutableLiveData<List<GardenModel>> gardenList = new MutableLiveData<>();
    private final MutableLiveData<List<FilterModel>> plantFilterList = new MutableLiveData<>();
    private final MutableLiveData<String> selectedFilter = new MutableLiveData<>("Semua");
    private final MutableLiveData<LeaderboardsResModel> leaderboards = new MutableLiveData<>();

    public LiveData<List<FilterModel>> getPlantFilterList() {
        return plantFilterList;
    }
    public LiveData<String> getSelectedFilter() {
        return selectedFilter;
    }
    public LiveData<UserDetailRes> getUser() {
        return user;
    }

    public LiveData<PlantResListDataModel> getPlantList() {
        return plantList;
    }

    public LiveData<List<GardenModel>> getGardenList() {
        return gardenList;
    }

    public LiveData<LeaderboardsResModel> getLeaderboards() {
        return leaderboards;
    }
    @Inject
    public HomeViewModel(GardenUseCase gardenUseCase, PlantUseCase plantUseCase, UserUseCase userUseCase, LeaderboardsUseCase leaderboardsUseCase) {
        this.gardenUseCase = gardenUseCase;
        this.plantUseCase = plantUseCase;
        this.userUseCase = userUseCase;
        this.leaderboardsUseCase = leaderboardsUseCase;

        getGardens();
        getProfile();
        getRankUser();
        getPlants(Objects.requireNonNull(selectedFilter.getValue()));
    }

    public void processEvent(HomeViewEvent event) {
        if (event instanceof HomeViewEvent.OnRefresh) {
            getGardens();
            getProfile();
            getRankUser();
            getPlants(Objects.requireNonNull(selectedFilter.getValue()));
        } else if (event instanceof HomeViewEvent.OnChangedFilter) {
            selectedFilter.setValue(((HomeViewEvent.OnChangedFilter) event).getFilter().getId());
            getPlants(Objects.requireNonNull(selectedFilter.getValue()));
        }
    }

    private void getGardens() {
        gardenUseCase.getGardensAll(new ResponseCallback<List<GardenModel>>() {
            @Override
            public void onSuccess(BaseResultResponse<List<GardenModel>> response) {
                if (response.getData() != null) {
                    List<FilterModel> filterModels = FilterModel.fromGardenModelList(response.getData());
                    plantFilterList.setValue(filterModels);
                    gardenList.postValue(response.getData());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<List<GardenModel>> response) {
                Log.d("Gardens:", "Error Gardens =" + response.getMessage());
            }
        });
    }
    private void getPlants(String gardenId) {
        Log.d("Plants:", "GardenIdViewModel =" + gardenId);
        if (gardenId.equals("Semua")){
            plantUseCase.getPlants(new ResponseCallback<PlantListRes>() {
                @Override
                public void onSuccess(BaseResultResponse<PlantListRes> response) {
                    if (response.getData() != null) {
                        Log.d("Plants:", "Plants Size =" + response.getData().data.getPlants().size());
                        plantList.postValue(response.getData().data);
                    }

                }

                @Override
                public void onFailure(BaseResultResponse<PlantListRes> response) {

                }

            });
        }else{
            plantUseCase.getPlantWithGarden(gardenId, new ResponseCallback<PlantListRes>() {
                @Override
                public void onSuccess(BaseResultResponse<PlantListRes> response) {
                    if (response.getData() != null) {
                        Log.d("Plants:", "Plants Size =" + response.getData().data.getPlants().size());
                        plantList.postValue(response.getData().data);
                    }

                }

                @Override
                public void onFailure(BaseResultResponse<PlantListRes> response) {

                }

            });
        }
    }


    private void getProfile() {
        userUseCase.getProfile(new ResponseCallback<UserDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<UserDetailRes> response) {
                if (response.getData() != null) {
                    user.setValue(response.getData());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<UserDetailRes> response) {

            }
        });
    }

    private void getRankUser(){
        leaderboardsUseCase.getDetailLeaderboards(new ResponseCallback<LeaderboardsDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<LeaderboardsDetailRes> response) {
                if (response.getData() != null) {
                    Log.d("Leaderboards:", "Leaderboards Size =");
                    leaderboards.setValue(response.getData().data.getLeaderboards());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<LeaderboardsDetailRes> response) {

            }
        });
    }
}
