package com.example.planties.data.reminder.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ReminderDetailRes {
    @SerializedName("data")
    public ReminderResDetailDataModel data;

    @SerializedName("message")
    public String message;

    @SerializedName("status")
    public String status;

}
