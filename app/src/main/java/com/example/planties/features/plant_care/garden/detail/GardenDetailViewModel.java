package com.example.planties.features.plant_care.garden.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.GardenType;
import com.example.planties.core.SortType;
import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenListRes;
import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.domain.garden.model.GardenModel;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.domain.plant.usecase.PlantUseCase;
import com.example.planties.features.utils.GardenFilter;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GardenDetailViewModel extends ViewModel {
    private final GardenUseCase gardenUseCase;
    private final PlantUseCase plantUseCase;

    @Inject
    public GardenDetailViewModel(GardenUseCase gardenUseCase, PlantUseCase plantUseCase) {
        this.gardenUseCase = gardenUseCase;
        this.plantUseCase = plantUseCase;
    }

    private final MutableLiveData<GardenResModel> gardenDetail = new MutableLiveData<>();
    private final MutableLiveData<PlantListRes> plantsList = new MutableLiveData<>();
    public LiveData<PlantListRes> getCurrentFilter() {
        return plantsList;
    }
    public LiveData<GardenResModel> getGardenList() {
        return gardenDetail;
    }

    private void getGardenDetail(String gardenId) {
        gardenUseCase.getDetailGarden(gardenId, new ResponseCallback<GardenDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<GardenDetailRes> response) {
                if (response.getData() != null) {
                    gardenDetail.setValue(response.getData().getData().getGarden());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<GardenDetailRes> response) {

            }
        });

    }

    private void getPlants(String gardenId) {
        plantUseCase.getPlantWithGarden(gardenId, new ResponseCallback<PlantListRes>() {
            @Override
            public void onSuccess(BaseResultResponse<PlantListRes> response) {
                if (response.getData() != null) {
                    plantsList.setValue(response.getData());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<PlantListRes> response) {

            }
        });
    }

}
