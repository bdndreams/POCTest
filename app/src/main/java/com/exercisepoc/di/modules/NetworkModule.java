package com.exercisepoc.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.exercisepoc.database.POCDatabase;
import com.exercisepoc.interactors.AlbumService;

import java.util.ConcurrentModificationException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class NetworkModule {
    String mBaseUrl;
    Context mContext;

    public NetworkModule(String baseUrl, Context context) {
        this.mBaseUrl = baseUrl;
        this.mContext = context;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mContext;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    AlbumService provideAlbumService(Retrofit retrofit, POCDatabase database) {
        return new AlbumService(retrofit, database);
    }

    @Provides
    @Singleton
    POCDatabase providePOCDatabase(Context context) {
        return POCDatabase.getDatabase(context);
    }
}
