package com.example.planties.data.garden.remote;

import com.example.planties.core.utils.NetworkUtil;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.data.garden.remote.dto.GardenListRes;
import com.example.planties.data.garden.remote.network.GardenService;

import retrofit2.Call;

public class GardenRemoteDataSoruceImpl implements GardenRemoteDataSource{
    private final GardenService GardenService;
    public GardenRemoteDataSoruceImpl(GardenService gardenService){
        this.GardenService = gardenService;
    }
    @Override
    public Call<GardenListRes> getGarden() {
        return GardenService.getGarden(NetworkUtil.getAuthHeader("")); //SharedPreference
    }

    @Override
    public Call<GardenDetailRes> postGarden(GardenReq gardenReq){
        return GardenService.postGarden(gardenReq, NetworkUtil.getAuthHeader(""));
    }

    @Override
    public Call<GardenDetailRes> getDetailGarden(String gardenId) {
        return GardenService.getDetailGarden(gardenId, NetworkUtil.getAuthHeader(""));
    }

    @Override
    public Call<GardenDetailRes> putGarden(String gardenId, GardenReq gardenReq) {
        return GardenService.putGarden(gardenId, gardenReq, NetworkUtil.getAuthHeader(""));
    }

    @Override
    public Call<GardenDetailRes> deleteGarden(String gardenId) {
        return GardenService.deleteGarden(gardenId, NetworkUtil.getAuthHeader(""));
    }
}
