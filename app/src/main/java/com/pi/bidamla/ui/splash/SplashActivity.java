package com.pi.bidamla.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;
import com.pi.bidamla.data.remote.UserModel;
import com.pi.bidamla.helper.LocalStorage;
import com.pi.bidamla.helper.Networks;
import com.pi.bidamla.network.apiServices.UserService;
import com.pi.bidamla.ui.auth.login.LoginActivity;
import com.pi.bidamla.ui.main.MainActivity;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @Inject
    Context context;

    @Inject
    UserService userService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setStatusBarColor();

        if (!Networks.isNetworkConnected(context)) {
            new MaterialDialog.Builder(SplashActivity.this)
                    .title(R.string.check_internet_title)
                    .content(R.string.check_internet_content)
                    .positiveText(R.string.ok_button)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            System.exit(0);
                        }
                    })
                    .cancelable(false)
                    .show();
            return;
        }

        navigate();
    }

    void navigate() {
        if (LocalStorage.getToken(context) == null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    navigateToLogin();
                }
            }, 2000);
        } else {
            userService.me().enqueue(new Callback<UserModel.UserResponse>() {
                @Override
                public void onResponse(Call<UserModel.UserResponse> call, Response<UserModel.UserResponse> response) {
                    if (response.isSuccessful()) {
                        LocalStorage.setUser(context, response.body());
                        navigateToMain();
                    } else {
                        navigateToLogin();
                    }
                }

                @Override
                public void onFailure(Call<UserModel.UserResponse> call, Throwable t) {
                    showErrorMessage();
                }
            });
        }
    }

    void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }

    void navigateToMain() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    void showErrorMessage() {
        new MaterialDialog.Builder(SplashActivity.this)
                .title(R.string.general_failure)
                .content(R.string.try_again)
                .positiveText(R.string.ok_button)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        System.exit(0);
                    }
                })
                .cancelable(false)
                .show();
    }

    void setStatusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
    }
}
