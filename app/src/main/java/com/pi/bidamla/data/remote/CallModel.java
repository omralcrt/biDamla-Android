package com.pi.bidamla.data.remote;

import com.google.gson.annotations.SerializedName;

public class CallModel {

    public class CallResponse {

        @SerializedName("id")
        private String id;
        @SerializedName("user")
        private UserModel.UserResponse user;
        @SerializedName("bloodRequestId")
        private String bloodRequestId;
        @SerializedName("createdAt")
        private String createdAt;
        @SerializedName("updatedAt")
        private String updatedAt;

        public CallResponse(String id, UserModel.UserResponse user, String bloodRequestId, String createdAt, String updatedAt) {
            this.id = id;
            this.user = user;
            this.bloodRequestId = bloodRequestId;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public UserModel.UserResponse getUser() {
            return user;
        }

        public void setUser(UserModel.UserResponse user) {
            this.user = user;
        }

        public String getBloodRequestId() {
            return bloodRequestId;
        }

        public void setBloodRequestId(String bloodRequestId) {
            this.bloodRequestId = bloodRequestId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    public static class CallRequest {

        @SerializedName("bloodRequestId")
        private String bloodRequestId;

        public CallRequest(String bloodRequestId) {
            this.bloodRequestId = bloodRequestId;
        }

        public String getBloodRequestId() {
            return bloodRequestId;
        }

        public void setBloodRequestId(String bloodRequestId) {
            this.bloodRequestId = bloodRequestId;
        }
    }

}
