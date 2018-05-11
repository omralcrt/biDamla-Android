package com.pi.bidamla.core;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.pi.bidamla.BuildConfig;
import com.pi.bidamla.R;
import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.helper.LocalStorage;
import com.pi.bidamla.network.ApiErrorUtils;
import com.pi.bidamla.ui.splash.SplashActivity;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Response;

@SuppressLint("Registered")
public class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    Context context;

    private ProgressDialog mProgressDialog;
    private int loadingStack = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCenter.start(getApplication(), BuildConfig.APPCENTER_SECRET, Analytics.class, Crashes.class);
    }

    protected void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this,
                    getString(R.string.loading_dialog_title),
                    getString(R.string.loading_dialog_desc));
        } else {
            if (mProgressDialog.isShowing()) {
                loadingStack++;
                return;
            }
            mProgressDialog.show();
        }
    }

    protected void hideLoading() {
        if (mProgressDialog != null) {
            if (loadingStack == 0) mProgressDialog.dismiss();
            else loadingStack--;
        }
    }

    protected void showMessage(final @StringRes int resId, Enums.MessageType messageType) {
        createMessage(getString(resId), messageType);
    }

    protected void showMessage(final CharSequence message, Enums.MessageType messageType) {
        createMessage(message, messageType);
    }

    private void createMessage(final CharSequence message, Enums.MessageType messageType) {
        TSnackbar snackbar = TSnackbar.make(this.findViewById(android.R.id.content), message, TSnackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);

        switch (messageType) {
            case SUCCESS:
                snackbarView.setBackgroundColor(Color.parseColor("#04B45F"));
                textView.setTextColor(Color.WHITE);
                break;
            case ERROR:
                snackbarView.setBackgroundColor(Color.parseColor("#FF0000"));
                textView.setTextColor(Color.WHITE);
                break;
            case INFO:
                snackbarView.setBackgroundColor(Color.parseColor("#3A99D9"));
                textView.setTextColor(Color.WHITE);
                break;
            default:
                snackbarView.setBackgroundColor(Color.parseColor("#F7FE2E"));
                textView.setTextColor(Color.WHITE);
                break;
        }
        snackbar.show();
    }

    protected void handleApiError(Response<?> response) {
        if (response.code() == 401) {
            LocalStorage.setUser(context, null);
            LocalStorage.setToken(context, null);
            Intent intentLogout = new Intent(context, SplashActivity.class);
            startActivity(intentLogout);
            finishAffinity();
        } else {
            BaseModel.BaseResponse errorResponse = ApiErrorUtils.parseError(response);
            showMessage(errorResponse.getMessage(), Enums.MessageType.ERROR);
        }
    }

}
