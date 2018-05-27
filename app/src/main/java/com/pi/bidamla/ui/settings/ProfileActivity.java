package com.pi.bidamla.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;
import com.pi.bidamla.data.remote.UserModel;
import com.pi.bidamla.helper.LocalStorage;
import com.pi.bidamla.ui.widget.BidamlaToolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity {

    @Inject
    Context context;

    @BindView(R.id.toolbar)
    BidamlaToolbar toolbar;
    @BindView(R.id.blood_group_text_view)
    TextView bloodGroupTextView;
    @BindView(R.id.phone_text_view)
    TextView phoneTextView;
    @BindView(R.id.email_text_view)
    TextView emailTextView;
    @BindView(R.id.count_text_view)
    TextView countTextView;

    UserModel.UserResponse user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        user = LocalStorage.getUser(context);

        init();
        setupViews();

    }

    void init() {
        toolbar.setTitle(user.getName() + " " + user.getLastName());
        toolbar.setListener(new BidamlaToolbar.BidamlaToolbarListener() {
            @Override
            public void closeButtonClicked() {
                exit();
            }
        });
    }

    void setupViews() {
        bloodGroupTextView.setText(user.getBloodGroup());
        phoneTextView.setText(user.getPhoneNumber());
        emailTextView.setText(user.getEmail());
        countTextView.setText(LocalStorage.getBloodRequestCount(context)+"");
    }

    void exit() {
        this.finish();
    }
}
