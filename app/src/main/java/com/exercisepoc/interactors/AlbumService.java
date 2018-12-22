package com.exercisepoc.interactors;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.exercisepoc.database.POCDatabase;
import com.exercisepoc.database.entity.Album;
import com.exercisepoc.models.AlbumResponse;
import com.exercisepoc.models.BaseResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class AlbumService {

    EndPoint mEndPoint;
    POCDatabase mDatabase;
    @Inject
    public AlbumService(Retrofit retrofit, POCDatabase database){
        mEndPoint = retrofit.create(EndPoint.class);
        mDatabase = database;
    }

    public void getAlbumListFromServer(){
        mEndPoint.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<AlbumResponse>>() {
                    @Override
                    public void onSuccess(List<AlbumResponse> baseResponse) {
                        insertAlbumInDB(baseResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public LiveData<List<Album>> getAlbumList(){
        return mDatabase.albumDao().getAllWords();
    }
    public void insertAlbumInDB(final List<AlbumResponse> baseResponse){
        Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {
                try {
                    List<Album> albumsList = new ArrayList<>();
                    for (AlbumResponse response : baseResponse) {
                        albumsList.add(response.getAlbum());
                    }
                    mDatabase.albumDao().insert(albumsList);
                    emitter.onSuccess(true);
                }catch (Exception e){
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    interface  EndPoint{

        @GET("/albums")
        Single<List<AlbumResponse>> getAlbums();
    }
}
