package com.pi.bidamla.network.apiServices;

import com.pi.bidamla.data.remote.BaseModel.BaseResponse;
import com.pi.bidamla.data.remote.UserModel.UserRequest;
import com.google.gson.internal.LinkedTreeMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AccountService {

    String endPoint = "account/";

    @FormUrlEncoded
    @POST("token?grant_type=password")
    Call<LinkedTreeMap>
    login(@Field("password") String password,
          @Field("username") String userName,
          @Field("grant_type") String grantType);

    @POST(endPoint + "register")
    Call<BaseResponse>
    register(@Body UserRequest userRequest);
}
