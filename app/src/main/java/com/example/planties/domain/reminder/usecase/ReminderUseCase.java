package com.example.planties.domain.reminder.usecase;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.reminder.remote.dto.ReminderDetailRes;
import com.example.planties.data.reminder.remote.dto.ReminderReq;
import com.example.planties.domain.reminder.repository.ReminderRepository;

public class ReminderUseCase {
    private final ReminderRepository reminderRepository;

    public ReminderUseCase(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public void postReminder(String gardenId, ReminderReq reminderReq, ResponseCallback<ReminderDetailRes> responseCallback) {
        reminderRepository.postReminder(gardenId, reminderReq, responseCallback);
    }

    public void getDetailReminder(String gardenId, String reminderId, ResponseCallback<ReminderDetailRes> responseCallback) {
        reminderRepository.getDetailReminder(gardenId, reminderId, responseCallback);
    }
}
