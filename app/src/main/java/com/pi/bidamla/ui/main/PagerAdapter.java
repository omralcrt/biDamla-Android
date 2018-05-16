package com.pi.bidamla.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pi.bidamla.ui.auth.login.LoginFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static int NUM_ITEMS = 4;

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
                return LoginFragment.newInstance();
            case 1:
                return LoginFragment.newInstance();
            case 2:
                return LoginFragment.newInstance();
            case 3:
                return LoginFragment.newInstance();
            default:
                return null;
        }

    }


}
