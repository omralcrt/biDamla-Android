package com.pi.bidamla.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;
import com.pi.bidamla.network.apiServices.UserService;
import com.pi.bidamla.ui.auth.login.LoginActivity;

import javax.inject.Inject;
public class SplashActivity extends BaseActivity {

    @Inject
    Context context;

    @Inject
    UserService userService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }
}
