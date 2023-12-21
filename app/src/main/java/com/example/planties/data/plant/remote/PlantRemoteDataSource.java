package com.example.planties.data.plant.remote;

import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.plant.remote.dto.PlantReq;

import retrofit2.Call;
import retrofit2.http.Body;

public interface PlantRemoteDataSource {
    Call<PlantListRes> getPlants();
    Call<PlantDetailRes> postPlant(String gardenId,@Body PlantReq plantReq);
    Call<PlantDetailRes> getDetailPlant(String gardenId,String plantId);
    Call<PlantDetailRes> putPlant(String gardenId,String plantId, @Body PlantReq plantReq);
    Call<PlantDetailRes> deletePlant(String gardentId,String plantId);
}
