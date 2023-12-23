package com.example.planties.features.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.user.remote.dto.UserDetailRes;
import com.example.planties.domain.garden.model.GardenModel;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.domain.plant.usecase.PlantUseCase;
import com.example.planties.domain.user.usecase.UserUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final GardenUseCase gardenUseCase;
    private final PlantUseCase plantUseCase;
    private final UserUseCase userUseCase;
    private final MutableLiveData<UserDetailRes> user = new MutableLiveData<>();
    private final MutableLiveData<PlantListRes> plantList = new MutableLiveData<>();
    private final MutableLiveData<List<GardenModel>> gardenList = new MutableLiveData<>();

    public LiveData<UserDetailRes> getUser() {
        return user;
    }
    public LiveData<PlantListRes> getPlantList() {
        return plantList;
    }
    public LiveData<List<GardenModel>> getGardenList() {
        return gardenList;
    }
    @Inject
    public HomeViewModel(GardenUseCase gardenUseCase, PlantUseCase plantUseCase, UserUseCase userUseCase) {
        this.gardenUseCase = gardenUseCase;
        this.plantUseCase = plantUseCase;
        this.userUseCase = userUseCase;

        getGardens();
        getProfile();
        getPlants();
    }

    public void processEvent(HomeViewEvent event){
        if(event instanceof HomeViewEvent.OnRefresh){
            getGardens();
            getProfile();
            getPlants();
        }
    }

    private void getGardens() {
        gardenUseCase.getGardens(new ResponseCallback<List<GardenModel>>() {
            @Override
            public void onSuccess(BaseResultResponse<List<GardenModel>> response) {
                if (response.getData() != null) {
                    Log.d("Gardens:", "Gardens List=" + response.getData());
                    for (GardenModel gardenModel : response.getData()) {
                        Log.d("Gardens:", "Gardens =" + gardenModel.getName());
                    }
                    gardenList.postValue(response.getData());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<List<GardenModel>> response) {
                Log.d("Gardens:", "Error Gardens =" + response.getMessage());
            }
        });
    }

    private void getDetailGarden(String id) {
        gardenUseCase.getDetailGarden(id, new ResponseCallback<GardenDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<GardenDetailRes> response) {
                if (response.getData() != null) {
                    Log.d("Gardens:", "Garden =" + response.getData().getData().getGarden().getName());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<GardenDetailRes> response) {

            }
        });
    }

    private void getPlants() {
        plantUseCase.getPlants(new ResponseCallback<PlantListRes>() {
            @Override
            public void onSuccess(BaseResultResponse<PlantListRes> response) {
                if (response.getData() != null) {
                    Log.d("Plants:", "Plants Size =" + response.getData().data.getPlants().size());
                    plantList.postValue(response.getData());
                }

            }

            @Override
            public void onFailure(BaseResultResponse<PlantListRes> response) {

            }

        });
    }

    private void getDetailPlant(String gardentId, String plantId) {
        plantUseCase.getDetailPlant(gardentId, plantId, new ResponseCallback<PlantDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<PlantDetailRes> response) {

            }

            @Override
            public void onFailure(BaseResultResponse<PlantDetailRes> response) {

            }
        });
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

}
