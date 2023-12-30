package com.example.planties.data.reminder.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReminderResListDataModel {
    @SerializedName("reminder")
    private List<ReminderResModel> mReminder;

    public List<ReminderResModel> getReminder() {
        return mReminder;
    }

    public void setReminder(List<ReminderResModel> reminder) {
        mReminder = reminder;
    }
}
