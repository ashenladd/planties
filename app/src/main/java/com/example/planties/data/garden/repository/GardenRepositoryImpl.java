package com.example.planties.data.garden.repository;

import androidx.annotation.NonNull;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.garden.remote.GardenRemoteDataSource;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.data.garden.remote.dto.GardenListRes;
import com.example.planties.domain.garden.repository.GardenRepository;

import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GardenRepositoryImpl implements GardenRepository {
    private final GardenRemoteDataSource gardenRemoteDataSource;

    @Inject
    public GardenRepositoryImpl(GardenRemoteDataSource gardenRemoteDataSource) {
        this.gardenRemoteDataSource = gardenRemoteDataSource;
    }

    @Override
    public void getGardens(ResponseCallback<GardenListRes> responseCallback) {
        gardenRemoteDataSource.getGarden().enqueue(new Callback<GardenListRes>() { //TODO token
            @Override
            public void onResponse(@NonNull Call<GardenListRes> call, @NonNull Response<GardenListRes> response) {
                if (response.isSuccessful()) {
                    GardenListRes gardenRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, gardenRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<GardenListRes> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void postGarden(GardenReq gardenReq, ResponseCallback<GardenDetailRes> responseCallback) {
        gardenRemoteDataSource.postGarden(gardenReq).enqueue(new Callback<GardenDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<GardenDetailRes> call, @NonNull Response<GardenDetailRes> response) {
                if (response.isSuccessful()) {
                    GardenDetailRes gardenRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, gardenRes, Objects.requireNonNull(gardenRes).getMessage(), response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<GardenDetailRes> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void getDetailGarden(String gardenId, ResponseCallback<GardenDetailRes> responseCallback) {
        gardenRemoteDataSource.getDetailGarden(gardenId).enqueue(new Callback<GardenDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<GardenDetailRes> call, @NonNull Response<GardenDetailRes> response) {
                if (response.isSuccessful()) {
                    GardenDetailRes gardenRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, gardenRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<GardenDetailRes> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void putGarden(String gardenId, GardenReq gardenReq, ResponseCallback<GardenDetailRes> responseCallback) {
        gardenRemoteDataSource.putGarden(gardenId, gardenReq).enqueue(new Callback<GardenDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<GardenDetailRes> call, @NonNull Response<GardenDetailRes> response) {
                if (response.isSuccessful()) {
                    GardenDetailRes gardenRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, gardenRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<GardenDetailRes> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void deleteGarden(String gardenId, ResponseCallback<GardenDetailRes> responseCallback) {
        gardenRemoteDataSource.deleteGarden(gardenId).enqueue(new Callback<GardenDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<GardenDetailRes> call, @NonNull Response<GardenDetailRes> response) {
                if (response.isSuccessful()) {
                    GardenDetailRes gardenRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, gardenRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<GardenDetailRes> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }


}
