package com.pi.bidamla.data.remote;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    public class UserResponse {

    }

    public class UserRequest {
        @SerializedName("email")
        private String email;
        @SerializedName("password")
        private String password;
        @SerializedName("name")
        private String name;
        @SerializedName("lastName")
        private String lastName;
        @SerializedName("phoneNumber")
        private String phoneNumber;
        @SerializedName("bloodGroup")
        private String bloodGroup;

        public UserRequest(String email, String password, String name, String lastName, String phoneNumber, String bloodGroup) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.bloodGroup = bloodGroup;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getBloodGroup() {
            return bloodGroup;
        }

        public void setBloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
        }
    }

}
