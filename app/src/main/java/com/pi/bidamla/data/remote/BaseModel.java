package com.pi.bidamla.data.remote;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

public class BaseModel{

    public class BaseResponse<T> {
        @SerializedName("status")
        private String status;
        @SerializedName("errorCode")
        private int errorCode;
        @SerializedName("errors")
        private List<LinkedTreeMap<String,String>> errors;
        @SerializedName("message")
        private String message;
        @SerializedName("data")
        private T Data;

        public BaseResponse() {
        }

        public BaseResponse(String status, List<LinkedTreeMap<String, String>> errors, String message, int errorCode, T data) {
            this.status = status;
            this.errorCode = errorCode;
            this.errors = errors;
            this.message = message;
            Data = data;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public List<LinkedTreeMap<String, String>> getErrors() {
            return errors;
        }

        public void setErrors(List<LinkedTreeMap<String, String>> errors) {
            this.errors = errors;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getData() {
            return Data;
        }

        public void setData(T data) {
            Data = data;
        }
    }

    public class ArrayResponse<T> {
        @SerializedName("count")
        private String count;
        @SerializedName("rows")
        private T[] rows;

        public ArrayResponse() {
        }

        public ArrayResponse(String count, T[] rows) {
            this.count = count;
            this.rows = rows;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public T[] getRows() {
            return rows;
        }

        public void setRows(T[] rows) {
            this.rows = rows;
        }
    }
}