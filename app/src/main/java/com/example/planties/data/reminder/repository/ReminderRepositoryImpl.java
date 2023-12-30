package com.example.planties.data.reminder.repository;

import androidx.annotation.NonNull;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.response.StatusResult;
import com.example.planties.data.reminder.remote.ReminderRemoteDataSource;
import com.example.planties.data.reminder.remote.dto.ReminderDetailRes;
import com.example.planties.data.reminder.remote.dto.ReminderListRes;
import com.example.planties.data.reminder.remote.dto.ReminderReq;
import com.example.planties.domain.reminder.repository.ReminderRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReminderRepositoryImpl implements ReminderRepository {
    private final ReminderRemoteDataSource reminderRemoteDataSource;

    @Inject
    public ReminderRepositoryImpl(ReminderRemoteDataSource reminderRemoteDataSource) {
        this.reminderRemoteDataSource = reminderRemoteDataSource;
    }

    @Override
    public void postReminder(String gardenId, ReminderReq reminderReq, ResponseCallback<ReminderDetailRes> responseCallback) {
        reminderRemoteDataSource.postReminder(gardenId, reminderReq).enqueue(new Callback<ReminderDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<ReminderDetailRes> call, @NonNull Response<ReminderDetailRes> response) {
                if (response.isSuccessful()) {
                    ReminderDetailRes reminderDetailRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, reminderDetailRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReminderDetailRes> call, Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void getReminder(String gardenId, ResponseCallback<ReminderListRes> responseCallback) {
        reminderRemoteDataSource.getReminder(gardenId).enqueue(new Callback<ReminderListRes>() {
            @Override
            public void onResponse(@NonNull Call<ReminderListRes> call, @NonNull Response<ReminderListRes> response) {
                if (response.isSuccessful()) {
                    ReminderListRes reminderListRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, reminderListRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReminderListRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }

    @Override
    public void getDetailReminder(String gardenId, String reminderId, ResponseCallback<ReminderDetailRes> responseCallback) {
        reminderRemoteDataSource.getDetailReminder(gardenId, reminderId).enqueue(new Callback<ReminderDetailRes>() {
            @Override
            public void onResponse(@NonNull Call<ReminderDetailRes> call, @NonNull Response<ReminderDetailRes> response) {
                if (response.isSuccessful()) {
                    ReminderDetailRes reminderDetailRes = response.body();
                    responseCallback.onSuccess(new BaseResultResponse<>(StatusResult.SUCCESS, reminderDetailRes, "success", response.code()));
                } else {
                    responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Failed", response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReminderDetailRes> call, @NonNull Throwable t) {
                responseCallback.onFailure(new BaseResultResponse<>(StatusResult.FAILURE, null, "Error: " + t.toString(), 0));
            }
        });
    }
}
