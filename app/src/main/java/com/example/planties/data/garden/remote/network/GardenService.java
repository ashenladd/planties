package com.example.planties.data.garden.remote.network;

import com.example.planties.core.Constant;
import com.example.planties.data.garden.remote.dto.GardenRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface GardenService {

    @GET(Constant.GARDEN)
    Call<GardenRes> getGarden(@HeaderMap String token);
}
