package com.pi.bidamla.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseFragment;
import com.pi.bidamla.helper.Constants;
import com.pi.bidamla.helper.LocalStorage;
import com.pi.bidamla.ui.auth.login.LoginActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsFragment extends BaseFragment implements SettingsRowAdapter.OnItemClickListener {

    @Inject
    Context context;

    @BindView(R.id.settings_recycler_view)
    RecyclerView settingsRecyclerView;

    List<String> rows = Constants.settingsMenuItems();

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        setUpRecyclerView();

        return view;
    }

    void setUpRecyclerView() {
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        SettingsRowAdapter rowAdapter = new SettingsRowAdapter(context, rows);
        rowAdapter.setOnItemClickListener(this);
        settingsRecyclerView.setAdapter(rowAdapter);
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(context, ProfileActivity.class);
                startActivity(intent);
                break;
            case 1:
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.logout_title)
                        .positiveText(R.string.yes_button)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                LocalStorage.setToken(context, null);
                                LocalStorage.setUser(context, null);
                                Intent intentLogout = new Intent(context, LoginActivity.class);
                                startActivity(intentLogout);
                                getActivity().finishAffinity();
                            }
                        })
                        .negativeText(R.string.cancel_button)
                        .cancelable(false)
                        .show();
                break;
            default:
                break;
        }
    }
}
