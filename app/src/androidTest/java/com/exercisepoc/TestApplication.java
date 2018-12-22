package com.exercisepoc;

import android.app.Application;

import com.exercisepoc.application.PocApplication;
import com.exercisepoc.di.components.AndroidComponents;
import com.exercisepoc.di.components.DaggerAndroidComponents;
import com.exercisepoc.di.modules.AppModule;
import com.exercisepoc.di.modules.NetworkModule;

public class TestApplication extends PocApplication{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void init() {

        if (mAndroidComponents== null) {
            mAndroidComponents = DaggerAndroidComponents.builder()
                    .appModule(new AppModule(this))
                    .networkModule(new NetworkModule("https://jsonplaceholder.typicode.com", this))
                    .build();
        }

    }

    @Override
    public AndroidComponents getAndroidComponents() {
        return super.getAndroidComponents();
    }
}
