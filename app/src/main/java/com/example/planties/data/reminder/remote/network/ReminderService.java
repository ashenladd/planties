package com.example.planties.data.reminder.remote.network;

import com.example.planties.core.Constant;
import com.example.planties.data.reminder.remote.dto.ReminderDetailRes;
import com.example.planties.data.reminder.remote.dto.ReminderReq;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReminderService {
    @POST(Constant.REMINDER)
    Call<ReminderDetailRes> postReminder(@Body ReminderReq reminderReq, @Path("gardenId") String gardenId, @HeaderMap Map<String, String> token);

    @GET(Constant.DETAIL_REMINDER)
    Call<ReminderDetailRes> getDetailReminder(@Path("gardenId") String gardenId, @Path("reminderId") String reminderId, @HeaderMap Map<String, String> token);
}
