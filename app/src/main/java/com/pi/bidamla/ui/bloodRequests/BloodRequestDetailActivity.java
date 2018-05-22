package com.pi.bidamla.ui.bloodRequests;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class BloodRequestDetailActivity extends BaseActivity {

    @Inject
    Context context;

    @BindView(R.id.toolbar)
    BidamlaToolbar toolbar;

    @BindView(R.id.blood_group_text_view)
    TextView bloodGroupTextView;
    @BindView(R.id.status_text_view)
    TextView statusTextView;
    @BindView(R.id.name_text_view)
    TextView nameTextView;
    @BindView(R.id.date_text_view)
    TextView dateTextView;
    @BindView(R.id.hospital_text_view)
    TextView hospitalTextView;

    BloodRequestModel.BloodRequestResponse bloodRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request_detail);
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
        String status = (bloodRequest.getRequestStatus().equals("waiting")) ? getResources().getString(R.string.waiting) : getResources().getString(R.string.completed);
        statusTextView.setText("(" + status + ")");
        statusTextView.setTextColor((bloodRequest.getRequestStatus().equals("waiting")) ? getResources().getColor(R.color.colorPrimary) : getResources().getColor(R.color.green));
        nameTextView.setText(bloodRequest.getUser().getName());
        dateTextView.setText(Utils.dateFormatter(bloodRequest.getCreatedAt()));
        hospitalTextView.setText("Hospital");
    }

    void init() {
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
