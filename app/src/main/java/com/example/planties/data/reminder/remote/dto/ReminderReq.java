package com.example.planties.data.reminder.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ReminderReq {
    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    @SerializedName("duration")
    public int duration;

    public ReminderReq(String name, String type, int duration) {
        this.name = name;
        this.type = type;
        this.duration = duration;
    }
}
