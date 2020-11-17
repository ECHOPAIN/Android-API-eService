package com.example.android_api_eservice;

import android.app.Application;
import com.example.android_api_eservice.data.di.FakeDependencyInjection;

public class PokeDataApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FakeDependencyInjection.setContext(this);
    }

}