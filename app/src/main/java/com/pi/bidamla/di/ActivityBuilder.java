package com.pi.bidamla.di;

import com.pi.bidamla.ui.auth.login.LoginActivity;
import com.pi.bidamla.ui.auth.login.LoginFragment;
import com.pi.bidamla.ui.auth.register.RegisterActivity;
import com.pi.bidamla.ui.auth.register.RegisterFragment;
import com.pi.bidamla.ui.bloodRequests.BloodRequestDetailActivity;
import com.pi.bidamla.ui.bloodRequests.BloodRequestsFragment;
import com.pi.bidamla.ui.bloodRequests.CreateBloodRequestActivity;
import com.pi.bidamla.ui.main.MainActivity;
import com.pi.bidamla.ui.myRequests.MyRequestDetailActivity;
import com.pi.bidamla.ui.myRequests.MyRequestsFragment;
import com.pi.bidamla.ui.notifications.NotificationsFragment;
import com.pi.bidamla.ui.settings.SettingsFragment;
import com.pi.bidamla.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector
    abstract RegisterFragment bindRegisterFragment();

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract BloodRequestsFragment bindBloodRequestsFragment();

    @ContributesAndroidInjector
    abstract MyRequestsFragment bindMyRequestsFragment();

    @ContributesAndroidInjector
    abstract NotificationsFragment bindNotificationsFragmentFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment bindSettingsFragmentFragment();

    @ContributesAndroidInjector
    abstract BloodRequestDetailActivity bindBloodRequestDetailActivity();

    @ContributesAndroidInjector
    abstract MyRequestDetailActivity bindMyRequestDetailActivity();

    @ContributesAndroidInjector
    abstract CreateBloodRequestActivity bindCreateBloodRequestActivity();

}
