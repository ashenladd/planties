package com.example.planties.features.auth.register;

import android.util.Log;

public abstract class RegisterViewEvent {
    public static class RegisterButtonClicked extends RegisterViewEvent {
        private final String username, password, name;

        public RegisterButtonClicked(String username, String password, String name) {
            this.username = username;
            this.password = password;
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }
    }

}
