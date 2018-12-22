package com.exercisepoc;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.exercisepoc.database.POCDatabase;
import com.exercisepoc.database.dao.AlbumDao;
import com.exercisepoc.database.entity.Album;
import com.exercisepoc.interactors.AlbumService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.exercisepoc", appContext.getPackageName());
    }
    POCDatabase mPOCDB;
    AlbumDao mAlbumDao;
    @Before
    public void setUP()
    {
        Context appContext = InstrumentationRegistry.getTargetContext();

        mPOCDB = Room.inMemoryDatabaseBuilder(appContext, POCDatabase.class)
                .allowMainThreadQueries().build();
        mAlbumDao = mPOCDB.albumDao();
    }

    @After
    public void cleanUp(){
        mPOCDB.close();
    }

    @Test
    public void getAlbumTest() throws InterruptedException {
        ArrayList<Album> albums = new ArrayList<>();
        for(int i=0; i<3; i++){
            Album album = new Album();
            album.setAlbumName("TEst "+i);
            album.setUserId(1);
            album.setId(i);
            albums.add(album);
        }
        mAlbumDao.insert(albums);


        List<Album> allWords = LiveDataTestUtil.getData(mAlbumDao.getAllWords());
        assertEquals(allWords.size(), 3);
        assertEquals(allWords.get(allWords.size()-1).getId(), allWords.size()-1);
    }
}
