package com.pi.bidamla.network.apiServices;

import com.google.gson.internal.LinkedTreeMap;
import com.pi.bidamla.data.remote.UserModel;
import com.pi.bidamla.data.remote.UserModel.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    String endPoint = "users/";

    @POST(endPoint)
    Call<LinkedTreeMap>
    register(@Body UserModel.UserRequest userRequest);

    @GET(endPoint + "me")
    Call<UserResponse> me();
}
