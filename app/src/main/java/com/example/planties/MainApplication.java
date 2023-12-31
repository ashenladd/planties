package com.example.planties;

import android.app.Application;
import android.content.Intent;

import com.example.planties.core.jwt.TokenHandler;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MainApplication extends Application {
    @Inject
    TokenHandler tokenHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        Intent intent = new Intent(this, MainActivity.class);

        if (tokenHandler.getAccessToken() != null && tokenHandler.getRefreshToken() != null && tokenHandler.getRole() != null) {
            if (tokenHandler.getRole().equals("client")) {
                intent.putExtra("destination", "homeFragment2");
            } else if (tokenHandler.getRole().equals("admin")) {
                intent.putExtra("destination", "adminFragment");
            }
        } else {
            // User is not authenticated, add a flag or extra to indicate the destination
            intent.putExtra("destination", "loginFragment");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
