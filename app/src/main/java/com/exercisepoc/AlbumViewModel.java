package com.exercisepoc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.exercisepoc.database.entity.Album;
import com.exercisepoc.interactors.AlbumService;

import java.util.List;

public class AlbumViewModel extends AndroidViewModel {

    private LiveData<List<Album>> mAllWords;
    private AlbumService mAlbumService;

    public AlbumViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Album>> getAllWords() {
        mAllWords = mAlbumService.getAlbumList();
        return mAllWords;
    }

    public void setAllWords(LiveData<List<Album>> mAllWords) {
        this.mAllWords = mAllWords;
    }

    public void setAlbumService(AlbumService mAlbumService) {
        this.mAlbumService = mAlbumService;
    }

}
