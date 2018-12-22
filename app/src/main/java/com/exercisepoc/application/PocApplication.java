package com.exercisepoc.application;

import android.app.Application;

import com.exercisepoc.di.components.AndroidComponents;
import com.exercisepoc.di.components.DaggerAndroidComponents;
import com.exercisepoc.di.modules.AppModule;
import com.exercisepoc.di.modules.NetworkModule;

public class PocApplication extends Application{
    protected AndroidComponents mAndroidComponents;
    @Override
    public void onCreate() {
        super.onCreate();

    }

    protected  void init(){
        mAndroidComponents = DaggerAndroidComponents.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("https://jsonplaceholder.typicode.com", this))
                .build();
    }
    public AndroidComponents getAndroidComponents() {
        return mAndroidComponents;
    }
}
