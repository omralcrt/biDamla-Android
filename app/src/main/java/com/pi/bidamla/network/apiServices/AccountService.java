package com.pi.bidamla.network.apiServices;

import com.pi.bidamla.data.remote.AccountModel;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AccountService {

    String endPoint = "auth/";

    @POST(endPoint)
    Call<AccountModel.TokenResponse> login();
}
