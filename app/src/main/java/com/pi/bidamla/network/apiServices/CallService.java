package com.pi.bidamla.network.apiServices;

import com.google.gson.internal.LinkedTreeMap;
import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.CallModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CallService {

    String endPoint = "calls/";

    @GET(endPoint + "bloodrequests/{id}")
    Call<BaseModel.ArrayResponse<CallModel.CallResponse>>
    listCalls(@Path("id") String id);

    @POST(endPoint)
    Call<LinkedTreeMap>
    createCall(@Body CallModel.CallRequest callRequest);

}
