package com.pi.bidamla.ui.auth.register;

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

public class RegisterFragment extends BaseFragment {

    @Inject
    Context context;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}