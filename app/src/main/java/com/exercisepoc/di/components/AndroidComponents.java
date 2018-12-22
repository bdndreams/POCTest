package com.exercisepoc.di.components;

import com.exercisepoc.MainActivity;
import com.exercisepoc.di.modules.AppModule;
import com.exercisepoc.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetworkModule.class})
public interface AndroidComponents {
    void inject(MainActivity mainActivity);
}
