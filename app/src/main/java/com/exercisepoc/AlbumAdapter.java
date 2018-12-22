package com.exercisepoc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercisepoc.database.entity.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> mAlbumList = new ArrayList<>();
    @Override
    public int getItemCount() {
        return mAlbumList.size();
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_layout, parent, false);
        return new AlbumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.mAlbumTitle.setText(mAlbumList.get(position).getAlbumName());
    }

    void setAlbumList(List<Album> albumList){
        mAlbumList = albumList;
        notifyDataSetChanged();
    }
    class AlbumViewHolder extends RecyclerView.ViewHolder{

        TextView mAlbumTitle;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            mAlbumTitle = itemView.findViewById(R.id.title);
        }
    }
}
