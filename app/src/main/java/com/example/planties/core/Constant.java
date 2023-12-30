package com.example.planties.core;

public class Constant {
    public static final String API_BASE = "http://13.213.42.123:8080";
    public static final String API_AI = "http://13.213.42.123:5000";
    public static final String AUTH = "/authentications";
    public static final String GARDEN = "/gardens";
    public static final String DETAIL_GARDEN = "/gardens/{gardenId}";
    public static final String PLANT = "/plants";
    public static final String GARDEN_PLANT = "/gardens/{gardenId}/plants";
    public static final String DETAIL_PLANT = "/gardens/{gardenId}/plants/{plantId}";
    public static final String PROFILE = "/profile";
    public static final String LEADERBOARDS = "/leaderboards";
    public static final String DETAIL_LEADERBOARD = "/leaderboards/{userId}";
    public static final String USER = "/users";
    public static final String PREDICT = "/predict";
    public static final String REMINDER = "gardens/{gardenId}/reminders";
    public static final String DETAIL_REMINDER = "gardens/{gardenId}/reminders/{reminderId}";
}

