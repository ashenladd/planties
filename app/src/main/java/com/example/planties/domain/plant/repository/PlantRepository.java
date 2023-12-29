package com.example.planties.domain.plant.repository;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.plant.remote.dto.PlantReq;
import com.example.planties.data.plant.remote.dto.PlantReqPut;

public interface PlantRepository {
    void getPlants(ResponseCallback<PlantListRes> responseCallback);

    void postPlant(String gardenId,PlantReq plantReq, ResponseCallback<PlantDetailRes> responseCallback);
    void getPlantsWithGarden(String gardenId,ResponseCallback<PlantListRes> responseCallback);

    void getDetailPlant(String gardenId, String plantId, ResponseCallback<PlantDetailRes> responseCallback);

    void putPlant(String gardenId, String plantId, PlantReqPut plantReq, ResponseCallback<PlantDetailRes> responseCallback);

    void deletePlant(String gardentId, String plantId, ResponseCallback<PlantDetailRes> responseCallback);
}
