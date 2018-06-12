package com.pi.bidamla.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pi.bidamla.ui.bloodRequests.BloodRequestsFragment;
import com.pi.bidamla.ui.myRequests.MyRequestsFragment;
import com.pi.bidamla.ui.notifications.NotificationsFragment;
import com.pi.bidamla.ui.settings.SettingsFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static int NUM_ITEMS = 3;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BloodRequestsFragment.newInstance();
            case 1:
                return MyRequestsFragment.newInstance();
            case 2:
                return SettingsFragment.newInstance();
            default:
                return null;
        }

    }


}
