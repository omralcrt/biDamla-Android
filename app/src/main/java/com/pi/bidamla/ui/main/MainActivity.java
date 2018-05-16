package com.pi.bidamla.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.pi.bidamla.R;
import com.pi.bidamla.core.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements AHBottomNavigation.OnTabSelectedListener {

    @Inject
    Context context;

    @BindView(R.id.view_pager)
    NonSwipeableViewPager mMainViewPager;

    @BindView(R.id.bottom_navigation)
    public AHBottomNavigation mBottomNavigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpBottomBar();
    }

    void setUpBottomBar() {
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation);
        navigationAdapter.setupWithBottomNavigation(mBottomNavigation);
        mBottomNavigation.setNotificationMarginLeft(20, 20);
        mBottomNavigation.setAccentColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mBottomNavigation.setInactiveColor(ContextCompat.getColor(context, R.color.gray));
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mBottomNavigation.setOnTabSelectedListener(this);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if (!wasSelected)
            mMainViewPager.setCurrentItem(position, false);
        return true;
    }
}
