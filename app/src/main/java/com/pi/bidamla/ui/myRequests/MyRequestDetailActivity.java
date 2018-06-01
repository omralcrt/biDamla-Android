package com.pi.bidamla.ui.myRequests;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;
import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.BloodRequestModel;
import com.pi.bidamla.data.remote.CallModel;
import com.pi.bidamla.helper.Constants;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.helper.Utils;
import com.pi.bidamla.network.apiServices.BloodRequestService;
import com.pi.bidamla.network.apiServices.CallService;
import com.pi.bidamla.ui.bloodRequests.BloodRequestDetailActivity;
import com.pi.bidamla.ui.bloodRequests.BloodRequestRowAdapter;
import com.pi.bidamla.ui.bloodRequests.CreateBloodRequestActivity;
import com.pi.bidamla.ui.widget.BidamlaToolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRequestDetailActivity extends BaseActivity implements CallRowAdapter.OnItemClickListener {

    @Inject
    Context context;

    @Inject
    CallService callService;

    @Inject
    BloodRequestService bloodRequestService;

    @BindView(R.id.toolbar)
    BidamlaToolbar toolbar;

    @BindView(R.id.blood_group_text_view)
    TextView bloodGroupTextView;
    @BindView(R.id.date_text_view)
    TextView dateTextView;
    @BindView(R.id.hospital_text_view)
    TextView hospitalTextView;
    @BindView(R.id.calls_recycler_view)
    RecyclerView callsRecyclerView;

    BloodRequestModel.BloodRequestResponse bloodRequest;
    List<CallModel.CallResponse> rows = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request_detail);
        ButterKnife.bind(this);

        if (getIntent() == null) {
            finish();
            showMessage(R.string.general_failure, Enums.MessageType.ERROR);
            return;
        }

        Gson gson = new Gson();
        bloodRequest = gson.fromJson(getIntent().getExtras().getString(Constants.BLOOD_REQUEST), BloodRequestModel.BloodRequestResponse.class);

        init();

        setupViews();
        getCalls();
    }

    void setupViews() {
        bloodGroupTextView.setText(bloodRequest.getBloodGroup());
        dateTextView.setText(Utils.dateFormatter(bloodRequest.getCreatedAt()));
        hospitalTextView.setText(bloodRequest.getHospital().getName());
    }

    void setUpRecyclerView() {
        callsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        CallRowAdapter rowAdapter = new CallRowAdapter(context, rows);
        rowAdapter.setOnItemClickListener(this);
        callsRecyclerView.setAdapter(rowAdapter);
    }

    void getCalls() {
        showLoading();
        callService.listCalls(bloodRequest.getId()).enqueue(new Callback<BaseModel.ArrayResponse<CallModel.CallResponse>>() {
            @Override
            public void onResponse(Call<BaseModel.ArrayResponse<CallModel.CallResponse>> call, Response<BaseModel.ArrayResponse<CallModel.CallResponse>> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    rows = Arrays.asList(response.body().getRows());
                    setUpRecyclerView();
                } else {
                    showMessage(R.string.check_inputs, Enums.MessageType.ERROR);
                }
            }

            @Override
            public void onFailure(Call<BaseModel.ArrayResponse<CallModel.CallResponse>> call, Throwable t) {
                hideLoading();
                showMessage(R.string.general_failure, Enums.MessageType.ERROR);
            }
        });
    }

    void init() {
        toolbar.setTitle(getResources().getString(R.string.request_detail_title));
        toolbar.setListener(new BidamlaToolbar.BidamlaToolbarListener() {
            @Override
            public void closeButtonClicked() {
                exit();
            }
        });
    }

    void exit() {
        this.finish();
    }

    @Override
    public void onItemClick(int position) {
        showLoading();
        bloodRequestService.completeBloodRequest(bloodRequest.getId()).enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    new MaterialDialog.Builder(MyRequestDetailActivity.this)
                            .title(R.string.well_soon_title)
                            .positiveText(R.string.ok_button)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    setResult(2, new Intent());
                                    finish();
                                }
                            })
                            .cancelable(false)
                            .show();
                } else {
                    showMessage(R.string.check_inputs, Enums.MessageType.ERROR);
                }
            }

            @Override
            public void onFailure(Call<LinkedTreeMap> call, Throwable t) {
                hideLoading();
                showMessage(R.string.general_failure, Enums.MessageType.ERROR);
            }
        });
    }
}
