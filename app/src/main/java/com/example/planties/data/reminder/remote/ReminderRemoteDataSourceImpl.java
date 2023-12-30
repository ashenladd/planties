package com.example.planties.data.reminder.remote;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.core.utils.NetworkUtil;
import com.example.planties.data.reminder.remote.dto.ReminderDetailRes;
import com.example.planties.data.reminder.remote.dto.ReminderListRes;
import com.example.planties.data.reminder.remote.dto.ReminderReq;
import com.example.planties.data.reminder.remote.network.ReminderService;

import javax.inject.Inject;

import retrofit2.Call;

public class ReminderRemoteDataSourceImpl implements ReminderRemoteDataSource{
    private ReminderService reminderService;
    private TokenHandler tokenHandler;

    @Inject
    public ReminderRemoteDataSourceImpl(ReminderService reminderService, TokenHandler tokenHandler) {
        this.reminderService = reminderService;
        this.tokenHandler = tokenHandler;
    }
    @Override
    public Call<ReminderDetailRes> postReminder(String gardenId, ReminderReq reminderReq) {
        return reminderService.postReminder(reminderReq, gardenId, NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }

    @Override
    public Call<ReminderListRes> getReminder(String gardenId) {
        return reminderService.getReminder(gardenId, NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }

    @Override
    public Call<ReminderDetailRes> getDetailReminder(String gardenId, String reminderId) {
        return reminderService.getDetailReminder(gardenId, reminderId, NetworkUtil.getAuthHeader(tokenHandler.getAccessToken()));
    }
}
