package com.pi.bidamla.ui.auth.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.internal.LinkedTreeMap;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseFragment;
import com.pi.bidamla.data.remote.UserModel;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.helper.Keyboard;
import com.pi.bidamla.helper.LocalStorage;
import com.pi.bidamla.network.apiServices.UserService;
import com.pi.bidamla.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends BaseFragment {

    @Inject
    Context context;

    @Inject
    UserService userService;

    @BindView(R.id.firstname_edit_text)
    EditText firstnameEditText;
    @BindView(R.id.lastname_edit_text)
    EditText lastnameEditText;
    @BindView(R.id.phone_number_edit_text)
    EditText phoneNumberEditText;
    @BindView(R.id.email_edit_text)
    EditText emailEditText;
    @BindView(R.id.blood_group_edit_text)
    EditText bloodGroupEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;

    Enums.BloodGroup[] items = Enums.BloodGroup.values();
    List<String> bloodGroups = new ArrayList<>();
    String selectedBloodGroup = "";

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        createBloodGroupList();

        return view;
    }

    void createBloodGroupList() {
        for (Enums.BloodGroup item : items) {
            bloodGroups.add(item.toString());
        }
    }

    private MaterialDialog buildBloodGroupDialog() {
        return new MaterialDialog.Builder(getActivity())
                .title(R.string.blood_groups)
                .items(bloodGroups)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        selectedBloodGroup = items[which].toEnum();
                        bloodGroupEditText.setText(bloodGroups.get(which));
                    }
                })
                .show();
    }

    @OnClick(R.id.register_button)
    void registerButtonClicked() {

        String name = firstnameEditText.getText().toString();
        String lastName = lastnameEditText.getText().toString();
        String phone = phoneNumberEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String bloodGroup = selectedBloodGroup;
        String password = passwordEditText.getText().toString();

        if (name.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty() ||
                bloodGroup.isEmpty() || password.isEmpty()) {
            showMessage(R.string.check_inputs, Enums.MessageType.ERROR);
            return;
        }

        UserModel.UserRequest userRequest = new UserModel.UserRequest(email, password, name, lastName, phone, bloodGroup);

        Keyboard.hideSoftKeyboard(getActivity());
        showLoading();
        userService.register(userRequest).enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    LocalStorage.setToken(context, (String) response.body().get("token"));
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    getActivity().finishAffinity();
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

    @OnClick(R.id.blood_group_edit_text)
    void bloodGroupEditTextClicked() {
        buildBloodGroupDialog();
    }
}
