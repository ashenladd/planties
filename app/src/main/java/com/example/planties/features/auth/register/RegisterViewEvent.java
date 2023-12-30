package com.example.planties.features.auth.register;

import android.util.Log;

public abstract class RegisterViewEvent {
    public static class RegisterButtonClicked extends RegisterViewEvent {
        private final String username, password, name;

        public RegisterButtonClicked(String name, String username, String password) {
            this.name = name;
            this.username = username;
            this.password = password;
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
