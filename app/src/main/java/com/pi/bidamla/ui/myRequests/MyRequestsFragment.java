package com.pi.bidamla.ui.myRequests;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseFragment;
import com.pi.bidamla.data.remote.BaseModel;
import com.pi.bidamla.data.remote.BloodRequestModel;
import com.pi.bidamla.helper.Constants;
import com.pi.bidamla.helper.Enums;
import com.pi.bidamla.helper.LocalStorage;
import com.pi.bidamla.network.apiServices.UserService;
import com.pi.bidamla.ui.bloodRequests.CreateBloodRequestActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRequestsFragment extends BaseFragment implements MyRequestRowAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    Context context;

    @Inject
    UserService userService;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.my_requests_recycler_view)
    RecyclerView myRequestsRecyclerView;

    List<BloodRequestModel.BloodRequestResponse> rows = new ArrayList<>();

    public static MyRequestsFragment newInstance() {
        return new MyRequestsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_requests, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setRefreshing(true);
        getBloodRequests();

        return view;
    }

    void setUpRecyclerView() {
        myRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        MyRequestRowAdapter rowAdapter = new MyRequestRowAdapter(context, rows);
        rowAdapter.setOnItemClickListener(this);
        myRequestsRecyclerView.setAdapter(rowAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    void getBloodRequests() {
        userService.listMyRequests().enqueue(new Callback<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>>() {
            @Override
            public void onResponse(Call<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>> call,
                                   Response<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    rows = Arrays.asList(response.body().getRows());
                    LocalStorage.setBloodRequestCount(context, rows.size());
                    setUpRecyclerView();
                } else {
                    showMessage(R.string.check_inputs, Enums.MessageType.ERROR);
                }
            }

            @Override
            public void onFailure(Call<BaseModel.ArrayResponse<BloodRequestModel.BloodRequestResponse>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showMessage(R.string.general_failure, Enums.MessageType.ERROR);
            }
        });
    }

    @OnClick(R.id.fab_button)
    void fabClicked() {
        Intent intent = new Intent(context, CreateBloodRequestActivity.class);
        startActivityForResult(intent, 2);
    }

    @Override
    public void onRefresh() {
        getBloodRequests();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(context, MyRequestDetailActivity.class);
        Gson gson = new Gson();
        intent.putExtra(Constants.BLOOD_REQUEST, gson.toJson(rows.get(position)));
        startActivityForResult(intent, 2);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && this.getView() != null) {
            getBloodRequests();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            getBloodRequests();
        }
    }
}
