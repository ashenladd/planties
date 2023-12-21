package com.example.planties.features.home;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.domain.garden.model.GardenModel;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.domain.plant.usecase.PlantUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final GardenUseCase gardenUseCase;
    private final PlantUseCase plantUseCase;

    @Inject
    public HomeViewModel(GardenUseCase gardenUseCase, PlantUseCase plantUseCase) {

        this.gardenUseCase = gardenUseCase;
        this.plantUseCase = plantUseCase;
        getGardens();
    }


    private void getGardens() {
        gardenUseCase.getGardens(new ResponseCallback<List<GardenModel>>() {
            @Override
            public void onSuccess(BaseResultResponse<List<GardenModel>> response) {
                if (response.getData() != null) {
                    for (GardenModel gardenModel : response.getData()) {
                        Log.d("Gardens:", "Gardens =" + gardenModel.getName());
                    }
                }
                Log.d("Gardens:", "GardensSize =" + response.getMessage());
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
                    System.out.println(response.getData().getData().getName());
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

}
