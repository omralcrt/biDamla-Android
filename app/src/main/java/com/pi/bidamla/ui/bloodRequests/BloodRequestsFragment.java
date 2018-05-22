package com.pi.bidamla.ui.bloodRequests;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseFragment;
import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.BloodRequestModel;
import com.pi.bidamla.helper.Constants;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.network.apiServices.BloodRequestService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodRequestsFragment extends BaseFragment implements BloodRequestRowAdapter.OnItemClickListener {

    @Inject
    Context context;

    @Inject
    BloodRequestService bloodRequestService;

    @BindView(R.id.blood_requests_recycler_view)
    RecyclerView bloodRequestsRecyclerView;

    List<BloodRequestModel.BloodRequestResponse> rows = new ArrayList<>();

    public static BloodRequestsFragment newInstance() {
        return new BloodRequestsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_request, container, false);
        ButterKnife.bind(this, view);

        getBloodRequests();

        return view;
    }

    void setUpRecyclerView() {
        bloodRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        BloodRequestRowAdapter rowAdapter = new BloodRequestRowAdapter(context, rows);
        rowAdapter.setOnItemClickListener(this);
        bloodRequestsRecyclerView.setAdapter(rowAdapter);
    }

    void getBloodRequests() {
        showLoading();
        bloodRequestService.listBloodRequests().enqueue(new Callback<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>>() {
            @Override
            public void onResponse(Call<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>> call,
                                   Response<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    rows = Arrays.asList(response.body().getRows());
                    setUpRecyclerView();
                } else {
                    showMessage(R.string.check_inputs, Enums.MessageType.ERROR);
                }
            }

            @Override
            public void onFailure(Call<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>> call, Throwable t) {
                hideLoading();
                showMessage(R.string.general_failure, Enums.MessageType.ERROR);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(context, BloodRequestDetailActivity.class);
        Gson gson = new Gson();
        intent.putExtra(Constants.BLOOD_REQUEST, gson.toJson(rows.get(position)));
        startActivity(intent);
    }
}
