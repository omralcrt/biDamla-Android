package com.pi.bidamla.ui.auth.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.internal.LinkedTreeMap;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseFragment;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.helper.Keyboard;
import com.pi.bidamla.helper.LocalStorage;
import com.pi.bidamla.network.ApiClient;
import com.pi.bidamla.network.apiServices.AccountService;
import com.pi.bidamla.ui.auth.register.RegisterActivity;
import com.pi.bidamla.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends BaseFragment {

    @Inject
    Context context;

    @BindView(R.id.email_edit_text)
    EditText emailEditText;

    @BindView(R.id.password_edit_text)
    EditText passwordEditText;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.continue_button)
    void continueButtonClicked() {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            showMessage(R.string.check_inputs, Enums.MessageType.ERROR);
            return;
        }

        Keyboard.hideSoftKeyboard(getActivity());
        showLoading();
        AccountService loginService =
                ApiClient.createService(context, AccountService.class, email, password);
        Call<LinkedTreeMap> call = loginService.login();
        call.enqueue(new Callback<LinkedTreeMap>() {
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

    @OnClick(R.id.register_button)
    void registerButtonClicked() {
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(intent);
    }
}
