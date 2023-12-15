package com.example.planties.features.auth.login;

public abstract class LoginViewEvent {
    public static class LoginButtonClicked extends LoginViewEvent {
        private final String username;
        private final String password;

        public LoginButtonClicked(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

}
