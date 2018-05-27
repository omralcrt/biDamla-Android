package com.pi.bidamla.data.remote;

import com.google.gson.annotations.SerializedName;

public class AccountModel {

    public class TokenResponse {

        @SerializedName("token")
        private String token;
        @SerializedName("user")
        private UserModel.UserResponse user;

        public TokenResponse(String token, UserModel.UserResponse user) {
            this.token = token;
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserModel.UserResponse getUser() {
            return user;
        }

        public void setUser(UserModel.UserResponse user) {
            this.user = user;
        }
    }
}
