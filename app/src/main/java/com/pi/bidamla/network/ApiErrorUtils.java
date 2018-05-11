package com.pi.bidamla.network;

import android.support.annotation.Nullable;

import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.BaseModel.BaseResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ApiErrorUtils {
    @Nullable
    public static BaseResponse<?> parseError(Response<?> response) {
        Converter<ResponseBody, BaseResponse> converter = ApiClient.bodyConverter;
        try {
            return converter.convert(response.errorBody());
        } catch (IOException ee) {
            BaseResponse model = new BaseModel().new BaseResponse<>();
            model.setMessage("Oops! Something went wrong. Please try again later.");
            return model;
        }
    }
}
