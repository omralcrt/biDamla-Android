package com.pi.bidamla.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SettingsFragment extends BaseFragment {

    @Inject
    Context context;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
