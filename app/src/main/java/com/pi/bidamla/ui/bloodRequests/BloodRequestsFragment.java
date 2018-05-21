package com.pi.bidamla.ui.bloodRequests;

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

public class BloodRequestsFragment extends BaseFragment {

    @Inject
    Context context;

    public static BloodRequestsFragment newInstance() {
        return new BloodRequestsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_request, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
