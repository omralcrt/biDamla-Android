package com.pi.bidamla.network.apiServices;

import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.HospitalModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HospitalService {

    String endPoint = "hospitals/";

    @GET(endPoint)
    Call<BaseModel.ArrayResponse<HospitalModel.HospitalResponse>>
    listHospitals();

}
