package com.example.planties.data.garden.remote;

import com.example.planties.data.garden.remote.dto.GardenRes;
import com.example.planties.data.garden.remote.network.GardenService;

import retrofit2.Call;

public class GardenRemoteDataSoruceImpl implements GardenRemoteDataSource{
    private final GardenService GardenService;
    GardenRemoteDataSoruceImpl(GardenService gardenService){
        this.GardenService = gardenService;
    }
    @Override
    public Call<GardenRes> getGarden(String token) {
        return GardenService.getGarden("Bearer "); //SharedPreference
    }
}
