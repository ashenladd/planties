package com.example.planties.domain.home.repository;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.garden.remote.dto.GardenRes;

public interface GardenRepository {
    void getGardens(GardenRes gardenResDto, ResponseCallback<GardenRes> responseCallback);
}
