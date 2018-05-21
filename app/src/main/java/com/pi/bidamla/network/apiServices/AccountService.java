package com.pi.bidamla.network.apiServices;

import com.google.gson.internal.LinkedTreeMap;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AccountService {

    String endPoint = "auth/";

    @POST(endPoint)
    Call<LinkedTreeMap> login();
}
