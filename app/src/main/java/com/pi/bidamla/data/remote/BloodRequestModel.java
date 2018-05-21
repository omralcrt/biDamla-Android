package com.pi.bidamla.data.remote;

import com.google.gson.annotations.SerializedName;

public class BloodRequestModel {

    public class BloodRequestResponse {

        @SerializedName("id")
        private String id;
        @SerializedName("user")
        private UserModel.UserResponse user;
        @SerializedName("bloodGroup")
        private String bloodGroup;
        @SerializedName("requestStatus")
        private String requestStatus;
        @SerializedName("createdAt")
        private String createdAt;
        @SerializedName("updatedAt")
        private String updatedAt;

        public BloodRequestResponse(String id, UserModel.UserResponse user, String bloodGroup, String requestStatus, String createdAt, String updatedAt) {
            this.id = id;
            this.user = user;
            this.bloodGroup = bloodGroup;
            this.requestStatus = requestStatus;
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

        public String getBloodGroup() {
            return bloodGroup;
        }

        public void setBloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
        }

        public String getRequestStatus() {
            return requestStatus;
        }

        public void setRequestStatus(String requestStatus) {
            this.requestStatus = requestStatus;
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
}
