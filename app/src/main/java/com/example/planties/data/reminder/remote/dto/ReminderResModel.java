package com.example.planties.data.reminder.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ReminderResModel {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    @SerializedName("duration")
    public int duration;

    @SerializedName("garden_id")
    public String gardenId;
}
