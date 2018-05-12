package com.pi.bidamla.ui.auth.register;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;
import com.pi.bidamla.ui.auth.login.LoginFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @Inject
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_container, RegisterFragment.newInstance())
                .commit();
    }
}
