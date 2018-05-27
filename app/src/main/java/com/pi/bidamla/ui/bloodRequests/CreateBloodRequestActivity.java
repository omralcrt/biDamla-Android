package com.pi.bidamla.ui.bloodRequests;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.internal.LinkedTreeMap;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;
import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.BloodRequestModel;
import com.pi.bidamla.data.remote.HospitalModel;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.network.apiServices.BloodRequestService;
import com.pi.bidamla.network.apiServices.HospitalService;
import com.pi.bidamla.ui.widget.BidamlaToolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateBloodRequestActivity extends BaseActivity {

    @Inject
    Context context;

    @Inject
    HospitalService hospitalService;
    @Inject
    BloodRequestService bloodRequestService;

    @BindView(R.id.toolbar)
    BidamlaToolbar toolbar;

    @BindView(R.id.radio_group_pozitif)
    RadioGroup radioGroupPositive;
    @BindView(R.id.radio_group_negatif)
    RadioGroup radioGroupNegative;
    @BindView(R.id.hospital_edit_text)
    EditText hospitalEditText;

    Enums.BloodGroup[] items = Enums.BloodGroup.values();
    String selectedBloodGroup = "";
    List<HospitalModel.HospitalResponse> hospitals = new ArrayList<>();
    List<String> hospitalNames = new ArrayList<>();
    HospitalModel.HospitalResponse selectedHospital;

    boolean isChecking = false;
    int mCheckedId = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_blood_request);
        ButterKnife.bind(this);

        init();
        fetchHospitals();

    }

    void init() {
        toolbar.setTitle(getResources().getString(R.string.create_blood_request_title));
        toolbar.setListener(new BidamlaToolbar.BidamlaToolbarListener() {
            @Override
            public void closeButtonClicked() {
                exit();
            }
        });
        radioGroupPositive.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != -1 && isChecking) {
                    isChecking = false;
                    radioGroupNegative.clearCheck();
                    mCheckedId = i;
                }
                isChecking = true;
                radioButtonCheck(radioGroup.getCheckedRadioButtonId());
            }
        });
        radioGroupNegative.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != -1 && isChecking) {
                    isChecking = false;
                    radioGroupPositive.clearCheck();
                    mCheckedId = i;
                }
                isChecking = true;
                radioButtonCheck(radioGroup.getCheckedRadioButtonId());
            }
        });
    }

    void radioButtonCheck(int id) {
        switch (id) {
            case R.id.radio_button_ap:
                selectedBloodGroup = Enums.BloodGroup.AP.toEnum();
                break;
            case R.id.radio_button_an:
                selectedBloodGroup = Enums.BloodGroup.AN.toEnum();
                break;
            case R.id.radio_button_bp:
                selectedBloodGroup = Enums.BloodGroup.BP.toEnum();
                break;
            case R.id.radio_button_bn:
                selectedBloodGroup = Enums.BloodGroup.BN.toEnum();
                break;
            case R.id.radio_button_abp:
                selectedBloodGroup = Enums.BloodGroup.ABP.toEnum();
                break;
            case R.id.radio_button_abn:
                selectedBloodGroup = Enums.BloodGroup.ABN.toEnum();
                break;
            case R.id.radio_button_p0:
                selectedBloodGroup = Enums.BloodGroup.P0.toEnum();
                break;
            case R.id.radio_button_n0:
                selectedBloodGroup = Enums.BloodGroup.N0.toEnum();
                break;
            default:
                break;
        }
    }

    void fetchHospitals() {
        showLoading();
        hospitalService.listHospitals().enqueue(new Callback<BaseModel.ArrayResponse<HospitalModel.HospitalResponse>>() {
            @Override
            public void onResponse(Call<BaseModel.ArrayResponse<HospitalModel.HospitalResponse>> call,
                                   Response<BaseModel.ArrayResponse<HospitalModel.HospitalResponse>> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    hospitals = Arrays.asList(response.body().getRows());
                    for (HospitalModel.HospitalResponse hospital : hospitals) {
                        hospitalNames.add(hospital.getName());
                    }
                } else {
                    showMessage(R.string.general_failure, Enums.MessageType.ERROR);
                }
            }

            @Override
            public void onFailure(Call<BaseModel.ArrayResponse<HospitalModel.HospitalResponse>> call, Throwable t) {
                hideLoading();
                showMessage(R.string.general_failure, Enums.MessageType.ERROR);
            }
        });
    }

    private MaterialDialog buildHospitalDialog() {
        return new MaterialDialog.Builder(this)
                .title(R.string.hospitals)
                .items(hospitalNames)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        selectedHospital = hospitals.get(which);
                        hospitalEditText.setText(hospitalNames.get(which));
                    }
                })
                .show();
    }

    void exit() {
        this.finish();
    }

    @OnClick(R.id.hospital_edit_text)
    void hospitalEditTextClicked() {
        buildHospitalDialog();
    }

    @OnClick(R.id.create_button)
    void createButtonClicked() {
        if (selectedBloodGroup.isEmpty() || selectedHospital == null) {
            showMessage(R.string.check_blood_request, Enums.MessageType.ERROR);
            return;
        }

        BloodRequestModel.BloodRequest bloodRequest = new BloodRequestModel.BloodRequest(selectedBloodGroup, selectedHospital.getId());

        showLoading();
        bloodRequestService.createBloodRequest(bloodRequest).enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    new MaterialDialog.Builder(CreateBloodRequestActivity.this)
                            .title(R.string.created_blood_request)
                            .positiveText(R.string.ok_button)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    finish();
                                }
                            })
                            .cancelable(false)
                            .show();
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
}
