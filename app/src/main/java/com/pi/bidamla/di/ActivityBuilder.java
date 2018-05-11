package com.pi.bidamla.di;

import com.pi.bidamla.ui.auth.login.LoginActivity;
import com.pi.bidamla.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

}
