package com.example.planties.data.plant.repository;

import androidx.annotation.NonNull;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.plant.remote.PlantRemoteDataSource;
import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.plant.remote.dto.PlantReq;
import com.example.planties.domain.plant.repository.PlantRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantRepositoryImpl implements PlantRepository {
    private final PlantRemoteDataSource plantRemoteDataSource;

    @Inject
    public PlantRepositoryImpl(PlantRemoteDataSource plantRemoteDataSource) {
        this.plantRemoteDataSource = plantRemoteDataSource;
    }

    @Override
    public void getPlants(ResponseCallback<PlantListRes> responseCallback) {
        plantRemoteDataSource.getPlants().enqueue(new Callback<PlantListRes>() {
            @Override
            public void onResponse(@NonNull Call<PlantListRes> call, @NonNull Response<PlantListRes> response) {
                if (response.isSuccessful()) {
                    PlantListRes plantListRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, plantListRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlantListRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void postPlant(String gardenId, PlantReq plantReq, ResponseCallback<PlantDetailRes> responseCallback) {
        plantRemoteDataSource.postPlant(gardenId, plantReq).enqueue(new Callback<PlantDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<PlantDetailRes> call, @NonNull Response<PlantDetailRes> response) {
                if (response.isSuccessful()) {
                    PlantDetailRes plantDetailRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, plantDetailRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlantDetailRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void getDetailPlant(String gardenId, String plantId, ResponseCallback<PlantDetailRes> responseCallback) {
        plantRemoteDataSource.getDetailPlant(gardenId, plantId).enqueue(new Callback<PlantDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<PlantDetailRes> call, @NonNull Response<PlantDetailRes> response) {
                if (response.isSuccessful()) {
                    PlantDetailRes plantDetailRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, plantDetailRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlantDetailRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void putPlant(String gardenId, String plantId, PlantReq plantReq, ResponseCallback<PlantDetailRes> responseCallback) {
        plantRemoteDataSource.putPlant(gardenId, plantId, plantReq).enqueue(new Callback<PlantDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<PlantDetailRes> call, @NonNull Response<PlantDetailRes> response) {
                if (response.isSuccessful()) {
                    PlantDetailRes plantDetailRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, plantDetailRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlantDetailRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void deletePlant(String gardentId, String plantId, ResponseCallback<PlantDetailRes> responseCallback) {
        plantRemoteDataSource.deletePlant(gardentId, plantId).enqueue(new Callback<PlantDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<PlantDetailRes> call, @NonNull Response<PlantDetailRes> response) {
                if (response.isSuccessful()) {
                    PlantDetailRes plantDetailRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, plantDetailRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlantDetailRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }
}
