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
    private final PlantService PlantService;
    private final TokenHandler TokenHandler;

    @Inject
    public PlantRemoteDataSourceImpl(PlantService plantService, TokenHandler tokenHandler) {
        this.PlantService = plantService;
        this.TokenHandler = tokenHandler;
    }

    @Override
    public Call<PlantListRes> getPlants() {
        return PlantService.getPlants(NetworkUtil.getAuthHeader(TokenHandler.getAccessToken()));
    }

    @Override
    public Call<PlantDetailRes> postPlant(String gardenId, PlantReq plantReq) {
        return PlantService.postPlant(plantReq, gardenId, NetworkUtil.getAuthHeader(TokenHandler.getAccessToken()));
    }

    @Override
    public Call<PlantDetailRes> getDetailPlant(String gardenId, String plantId) {
        return PlantService.getDetailPlant(gardenId, plantId, NetworkUtil.getAuthHeader(TokenHandler.getAccessToken()));
    }

    @Override
    public Call<PlantDetailRes> putPlant(String gardenId, String plantId, PlantReq plantReq) {
        return PlantService.putPlant(plantReq, gardenId, plantId, NetworkUtil.getAuthHeader(TokenHandler.getAccessToken()));
    }

    @Override
    public Call<PlantDetailRes> deletePlant(String gardentId, String plantId) {
        return PlantService.deletePlant(gardentId, plantId, NetworkUtil.getAuthHeader(TokenHandler.getAccessToken()));
    }

}
