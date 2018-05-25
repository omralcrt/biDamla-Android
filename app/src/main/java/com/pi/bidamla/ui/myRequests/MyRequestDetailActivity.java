package com.pi.bidamla.ui.myRequests;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;
import com.pi.bidamla.data.remote.BloodRequestModel;
import com.pi.bidamla.helper.Constants;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.helper.Utils;
import com.pi.bidamla.ui.widget.BidamlaToolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRequestDetailActivity extends BaseActivity {

    @Inject
    Context context;

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
    }

    void setupViews() {
        bloodGroupTextView.setText(bloodRequest.getBloodGroup());
        dateTextView.setText(Utils.dateFormatter(bloodRequest.getCreatedAt()));
        hospitalTextView.setText(bloodRequest.getHospital().getName());
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
}
