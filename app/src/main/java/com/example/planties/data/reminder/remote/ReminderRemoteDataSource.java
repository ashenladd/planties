package com.example.planties.data.reminder.remote;

import com.example.planties.core.Constant;
import com.example.planties.data.reminder.remote.dto.ReminderDetailRes;
import com.example.planties.data.reminder.remote.dto.ReminderListRes;
import com.example.planties.data.reminder.remote.dto.ReminderReq;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReminderRemoteDataSource {
    Call<ReminderDetailRes> postReminder(String gardenId, @Body ReminderReq reminderReq);
    Call<ReminderListRes> getReminder(String gardenId);
    Call<ReminderDetailRes> getDetailReminder(String gardenId, String reminderId);

}
