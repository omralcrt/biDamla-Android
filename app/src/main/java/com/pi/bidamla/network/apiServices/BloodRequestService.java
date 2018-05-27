package com.pi.bidamla.network.apiServices;

import com.google.gson.internal.LinkedTreeMap;
import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.BloodRequestModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BloodRequestService {

    String endPoint = "bloodRequests/";

    @GET(endPoint)
    Call<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>>
    listBloodRequests();

    @POST(endPoint)
    Call<LinkedTreeMap>
    createBloodRequest(@Body BloodRequestModel.BloodRequest bloodRequest);
}
