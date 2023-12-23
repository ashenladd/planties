package com.example.planties.data.plant.remote;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.core.utils.NetworkUtil;
import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.plant.remote.dto.PlantReq;
import com.example.planties.data.plant.remote.network.PlantService;

import javax.inject.Inject;

import retrofit2.Call;

public class PlantRemoteDataSourceImpl implements PlantRemoteDataSource {
    private final PlantService plantService;
    private final TokenHandler tokenHandler;

    @Inject
    public PlantRemoteDataSourceImpl(PlantService plantService, TokenHandler tokenHandler) {
        this.plantService = plantService;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public Call<PlantListRes> getPlants() {
        return plantService.getPlants(NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }

    @Override
    public Call<PlantDetailRes> postPlant(String gardenId, PlantReq plantReq) {
        return plantService.postPlant(plantReq, gardenId, NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }

    @Override
    public Call<PlantDetailRes> getDetailPlant(String gardenId, String plantId) {
        return plantService.getDetailPlant(gardenId, plantId, NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }

    @Override
    public Call<PlantDetailRes> putPlant(String gardenId, String plantId, PlantReq plantReq) {
        return plantService.putPlant(plantReq, gardenId, plantId, NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }

    @Override
    public Call<PlantDetailRes> deletePlant(String gardentId, String plantId) {
        return plantService.deletePlant(gardentId, plantId, NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }

}
