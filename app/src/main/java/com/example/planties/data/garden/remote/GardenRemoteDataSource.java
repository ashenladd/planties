package com.example.planties.data.garden.remote;

import com.example.planties.data.garden.remote.dto.GardenRes;

import retrofit2.Call;
import retrofit2.http.HeaderMap;

public interface GardenRemoteDataSource {
    Call<GardenRes> getGarden(@HeaderMap String token);
}
