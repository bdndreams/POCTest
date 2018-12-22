package com.exercisepoc;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exercisepoc.application.PocApplication;
import com.exercisepoc.database.entity.Album;
import com.exercisepoc.interactors.AlbumService;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    AlbumService mAlbumService;
    private AlbumViewModel mAlbumViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((PocApplication) getApplication()).getAndroidComponents().inject(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final AlbumAdapter adapter = new AlbumAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAlbumViewModel = ViewModelProviders.of(this).get(AlbumViewModel.class);
        mAlbumViewModel.setAlbumService(mAlbumService);
        mAlbumViewModel.getAllWords().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable List<Album> albums) {
                adapter.setAlbumList(albums);
            }
        });
    }
}
