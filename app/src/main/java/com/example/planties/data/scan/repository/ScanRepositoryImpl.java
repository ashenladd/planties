package com.example.planties.data.scan.repository;

import androidx.annotation.NonNull;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.plant.remote.PlantRemoteDataSource;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.scan.remote.ScanRemoteDataSource;
import com.example.planties.data.scan.remote.dto.ScanRes;
import com.example.planties.domain.scan.repository.ScanRepository;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanRepositoryImpl implements ScanRepository {
    private final ScanRemoteDataSource scanRemoteDataSource;

    @Inject
    public ScanRepositoryImpl(ScanRemoteDataSource scanRemoteDataSource) {
        this.scanRemoteDataSource = scanRemoteDataSource;
    }

    @Override
    public void scan(MultipartBody.Part file, ResponseCallback<ScanRes> responseCallback) {
        scanRemoteDataSource.scan(file).enqueue(new Callback<ScanRes>() {
            @Override
            public void onResponse(@NonNull Call<ScanRes> call, @NonNull Response<ScanRes> response) {
                if (response.isSuccessful()) {
                    ScanRes scanRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, scanRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }
            @Override
            public void onFailure(@NonNull Call<ScanRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }
}
