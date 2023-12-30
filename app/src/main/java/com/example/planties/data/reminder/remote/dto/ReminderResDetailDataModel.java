package com.example.planties.data.reminder.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ReminderResDetailDataModel {
    @SerializedName("reminder")
    private ReminderResModel mReminder;

    public ReminderResModel getReminder() {
        return mReminder;
    }

    public void setReminder(ReminderResModel reminder) {
        mReminder = reminder;
    }
}
