package com.example.planties.features.plant_care.plant_detail;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.domain.plant.usecase.PlantUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlantDetailViewModel {
    private PlantUseCase plantUseCase;
    @Inject
    public PlantDetailViewModel(PlantUseCase plantUseCase) {
        this.plantUseCase = plantUseCase;
    }

    private void getDetailPlant(String gardenId, String plantId) {
        plantUseCase.getDetailPlant(gardenId, plantId, new ResponseCallback<PlantDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<PlantDetailRes> response) {

            }

            @Override
            public void onFailure(BaseResultResponse<PlantDetailRes> response) {

            }
        });
    }
}
