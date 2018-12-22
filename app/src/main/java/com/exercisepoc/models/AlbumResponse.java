package com.exercisepoc.models;

import com.exercisepoc.database.entity.Album;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumResponse {
    @JsonProperty("title")
    String mTitle;
    @JsonProperty("id")
    int mId;
    @JsonProperty("userId")
    int mUserId;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }


    public Album getAlbum(){
        Album album = new Album();
        album.setAlbumName(getTitle());
        album.setId(getId());
        album.setUserId(getUserId());
        return album;
    }
}
