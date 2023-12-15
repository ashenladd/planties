package com.example.planties.domain.garden.repository;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.data.garden.remote.dto.GardenListRes;

public interface GardenRepository {
    void getGardens(ResponseCallback<GardenListRes> responseCallback);
    void postGarden(GardenReq gardenReq,ResponseCallback<GardenDetailRes> responseCallback);

    void getDetailGarden(String gardenId, ResponseCallback<GardenDetailRes> responseCallback);
    void putGarden(String gardenId, GardenReq gardenReq,ResponseCallback<GardenDetailRes> responseCallback);

    void deleteGarden(String gardenId, ResponseCallback<GardenDetailRes> responseCallback);
}
