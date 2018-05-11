package com.pi.bidamla.network.apiServices;

import com.pi.bidamla.data.remote.UserModel.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    String endPoint = "users/";

    @GET(endPoint + "me")
    Call<UserResponse> me();
}
