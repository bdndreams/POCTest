package com.exercisepoc.models;

import java.util.List;

public class BaseResponse {
    List<AlbumResponse> mAlbumResponse;

    public List<AlbumResponse> getmAlbumResponse() {
        return mAlbumResponse;
    }

    public void setmAlbumResponse(List<AlbumResponse> mAlbumResponse) {
        this.mAlbumResponse = mAlbumResponse;
    }
}
