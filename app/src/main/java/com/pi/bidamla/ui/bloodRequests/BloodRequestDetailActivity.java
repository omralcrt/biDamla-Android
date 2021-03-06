package com.pi.bidamla.ui.bloodRequests;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;
import com.pi.bidamla.data.remote.BloodRequestModel;
import com.pi.bidamla.data.remote.CallModel;
import com.pi.bidamla.helper.Constants;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.helper.Utils;
import com.pi.bidamla.network.apiServices.CallService;
import com.pi.bidamla.ui.widget.BidamlaToolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodRequestDetailActivity extends BaseActivity {

    @Inject
    Context context;

    @Inject
    CallService callService;

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
        nameTextView.setText(bloodRequest.getUser().getName() + " " + bloodRequest.getUser().getLastName().substring(0, 1) + ".");
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

    @OnClick(R.id.call_button)
    void callNumberClicked() {
        showLoading();
        CallModel.CallRequest callRequest = new CallModel.CallRequest(bloodRequest.getId());
        callService.createCall(callRequest).enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:0" + bloodRequest.getUser().getPhoneNumber()));
                    startActivity(callIntent);
                } else {
                    showMessage(R.string.general_failure, Enums.MessageType.ERROR);
                }
            }

            @Override
            public void onFailure(Call<LinkedTreeMap> call, Throwable t) {
                hideLoading();
                showMessage(R.string.general_failure, Enums.MessageType.ERROR);
            }
        });
    }

    @OnClick(R.id.maps_image_view)
    void mapsClicked() {
        String uri = "https://www.google.com/maps/search/?api=1&query=" + bloodRequest.getHospital().getLat() + "," + bloodRequest.getHospital().getLng();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);
    }

    void exit() {
        this.finish();
    }
}
