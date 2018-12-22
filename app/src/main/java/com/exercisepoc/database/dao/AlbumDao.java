package com.exercisepoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.exercisepoc.database.entity.Album;

import java.util.List;

@Dao
public interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Album album);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Album> album);

    @Query("SELECT * from album_table ORDER BY albumName ASC")
    LiveData<List<Album>> getAllWords();
}
