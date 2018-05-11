package com.pi.bidamla.ui.auth.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @Inject
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_container, LoginFragment.newInstance())
                .commit();
    }

}
