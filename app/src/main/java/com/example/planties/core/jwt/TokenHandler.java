package com.example.planties.core.jwt;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.planties.data.auth.remote.AuthRemoteDataSource;

import javax.inject.Inject;

public class TokenHandler {

    private static final String PREF_NAME = "PlantiesPreferences";
    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private final Context context;
    @Inject
    public TokenHandler(Context context) {
        this.context = context;
    }

    // Save access token to SharedPreferences
    public void saveAccessToken(String accessToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    // Retrieve access token from SharedPreferences
    public String getAccessToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }

    // Clear access token from SharedPreferences
    public void clearAccessToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_ACCESS_TOKEN);
        editor.apply();
    }
}
