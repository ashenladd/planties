package com.example.planties.domain.plant.usecase;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.plant.remote.dto.PlantReq;
import com.example.planties.domain.plant.repository.PlantRepository;

import javax.inject.Inject;

public class PlantUseCase {
    private final PlantRepository plantRepository;

    @Inject
    public PlantUseCase(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public void getPlants(ResponseCallback<PlantListRes> responseCallback){
        plantRepository.getPlants(responseCallback);
    }

    public void postPlant(String gardenId, PlantReq plantReq, ResponseCallback<PlantDetailRes> responseCallback){
        plantRepository.postPlant(gardenId,plantReq,responseCallback);
    }

    public void getDetailPlant(String gardenId, String plantId, ResponseCallback<PlantDetailRes> responseCallback){
        plantRepository.getDetailPlant(gardenId,plantId,responseCallback);
    }

    public void putPlant(String gardenId, String plantId, PlantReq plantReq, ResponseCallback<PlantDetailRes> responseCallback){
        plantRepository.putPlant(gardenId,plantId,plantReq,responseCallback);
    }

    public void deletePlant(String gardentId, String plantId, ResponseCallback<PlantDetailRes> responseCallback){
        plantRepository.deletePlant(gardentId,plantId,responseCallback);
    }
}
