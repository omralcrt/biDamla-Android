package com.pi.bidamla.network.apiServices;

import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.BloodRequestModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BloodRequestService {

    String endPoint = "bloodRequests/";

    @GET(endPoint)
    Call<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>>
    listBloodRequests();
}
