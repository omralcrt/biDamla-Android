package com.pi.bidamla.ui.auth.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;
import com.pi.bidamla.network.apiServices.AccountService;
import com.pi.bidamla.ui.widget.BidamlaToolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @Inject
    Context context;

    @Inject
    AccountService accountService;

    @BindView(R.id.toolbar)
    BidamlaToolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();

    }

    void init() {
        toolbar.setListener(new BidamlaToolbar.ProjectNameToolbarListener() {
            @Override
            public void closeButtonClicked() {

            }
        });
    }

}
