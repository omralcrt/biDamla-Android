package com.pi.bidamla.ui.auth.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseFragment;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment {

    @Inject
    Context context;

    @BindView(R.id.blood_group_edit_text)
    EditText bloodGroupEditText;

    Enums.BloodGroup[] items = Enums.BloodGroup.values();
    List<String> bloodGroups = new ArrayList<>();
    String selectedBloodGroup = "";

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        createBloodGroupList();

        return view;
    }

    void createBloodGroupList() {
        for (Enums.BloodGroup item : items) {
            bloodGroups.add(item.toString());
        }
    }

    private MaterialDialog buildBloodGroupDialog() {
        return new MaterialDialog.Builder(getActivity())
                .title(R.string.blood_groups)
                .items(bloodGroups)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        selectedBloodGroup = items[which].toEnum();
                        bloodGroupEditText.setText(bloodGroups.get(which));
                    }
                })
                .show();
    }

    @OnClick(R.id.register_button)
    void registerButtonClicked() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.blood_group_edit_text)
    void bloodGroupEditTextClicked() {
        buildBloodGroupDialog();
    }
}
