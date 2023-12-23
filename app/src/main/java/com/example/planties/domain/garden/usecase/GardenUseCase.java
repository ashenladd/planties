package com.example.planties.domain.garden.usecase;

import android.util.Log;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenListRes;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.domain.garden.mapper.GardenMapper;
import com.example.planties.domain.garden.model.GardenModel;
import com.example.planties.domain.garden.repository.GardenRepository;

import java.util.List;

import javax.inject.Inject;

public class GardenUseCase {
    private final GardenRepository gardenRepository;

    //Kalau perlu mapper buat aja di model trus di Map
    @Inject
    public GardenUseCase(GardenRepository gardenRepository) {
        this.gardenRepository = gardenRepository;
    }

    public void getGardens(final ResponseCallback<List<GardenModel>> responseCallback) {
        gardenRepository.getGardens(new ResponseCallback<GardenListRes>() {
            //Mapping
            @Override
            public void onSuccess(BaseResultResponse<GardenListRes> response) {
                if (response.isSuccess()) {
                    GardenListRes gardenRes = response.getData();
                    List<GardenModel> gardenModels = GardenMapper.mapToModelList(gardenRes.getData().getGardens());
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, gardenModels, response.getMessage(), response.getCode()));
                }
            }

            @Override
            public void onFailure(BaseResultResponse<GardenListRes> response) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, response.getMessage(), response.getCode()));
            }
        });
    }

    public void postGarden(GardenReq gardenReq, ResponseCallback<GardenDetailRes> responseCallback) {
        gardenRepository.postGarden(gardenReq, responseCallback);
    }

    public void getDetailGarden(String gardenId, ResponseCallback<GardenDetailRes> responseCallback) {
        gardenRepository.getDetailGarden(gardenId, responseCallback);
    }

    public void putGarden(String gardenId, GardenReq gardenReq, ResponseCallback<GardenDetailRes> responseCallback) {
        gardenRepository.putGarden(gardenId, gardenReq, responseCallback);
    }

    public void deleteGarden(String gardenId, ResponseCallback<GardenDetailRes> responseCallback) {
        gardenRepository.deleteGarden(gardenId, responseCallback);
    }

}
