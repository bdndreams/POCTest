package com.exercisepoc.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.exercisepoc.database.dao.AlbumDao;
import com.exercisepoc.database.entity.Album;

@Database(entities = {Album.class}, version = 1)
public abstract class POCDatabase extends RoomDatabase{

    private static volatile POCDatabase INSTANCE;

    public abstract AlbumDao albumDao();

    public static POCDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (POCDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), POCDatabase.class,
                        "pocdatabase.db").build();
            }
        }
        return INSTANCE;
    }
}
