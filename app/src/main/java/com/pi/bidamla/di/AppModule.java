package com.pi.bidamla.di;

import android.app.Application;
import android.content.Context;

import com.pi.bidamla.network.ApiClient;
import com.pi.bidamla.network.apiServices.BloodRequestService;
import com.pi.bidamla.network.apiServices.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application){
        return application;
    }

    @Provides
    UserService provideUserService(Context context) {
        return ApiClient.createService(context, UserService.class);
    }

    @Provides
    BloodRequestService provideBloodRequestService(Context context) {
        return ApiClient.createService(context, BloodRequestService.class);
    }

}
