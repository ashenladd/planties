package com.example.planties.data.garden.remote;

import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.data.garden.remote.dto.GardenListRes;

import retrofit2.Call;
import retrofit2.http.Body;

public interface GardenRemoteDataSource {
    Call<GardenListRes> getGarden(String sorting, String type);
    Call<GardenListRes> getGardenAll();
    Call<GardenDetailRes> postGarden(@Body GardenReq gardenReq);

    Call<GardenDetailRes> getDetailGarden(String gardenId);
    Call<GardenDetailRes> putGarden(String gardenId, @Body GardenReq gardenReq);
    Call<GardenDetailRes> deleteGarden(String gardenId);
}
