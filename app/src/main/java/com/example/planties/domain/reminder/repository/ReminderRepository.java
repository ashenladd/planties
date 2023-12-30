package com.example.planties.domain.reminder.repository;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.reminder.remote.dto.ReminderDetailRes;
import com.example.planties.data.reminder.remote.dto.ReminderListRes;
import com.example.planties.data.reminder.remote.dto.ReminderReq;

public interface ReminderRepository {
    void postReminder(String gardenId, ReminderReq reminderReq, ResponseCallback<ReminderDetailRes> responseCallback);
    void getReminder(String gardenId, ResponseCallback<ReminderListRes> responseCallback);
    void getDetailReminder(String gardenId, String reminderId, ResponseCallback<ReminderDetailRes> responseCallback);
}
