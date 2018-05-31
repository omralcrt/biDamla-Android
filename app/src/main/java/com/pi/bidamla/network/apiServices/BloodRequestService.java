package com.pi.bidamla.network.apiServices;

import com.google.gson.internal.LinkedTreeMap;
import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.BloodRequestModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BloodRequestService {

    String endPoint = "bloodRequests/";

    @GET(endPoint)
    Call<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>>
    listBloodRequests();

    @POST(endPoint)
    Call<LinkedTreeMap>
    createBloodRequest(@Body BloodRequestModel.BloodRequest bloodRequest);

    @PUT(endPoint + "{id}/complete")
    Call<LinkedTreeMap>
    completeBloodRequest(@Path("id") String id);
}
