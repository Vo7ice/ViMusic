package com.greenorange.vimusic.repository;

import android.content.Context;

import com.greenorange.vimusic.Constants;
import com.greenorange.vimusic.loader.MusicLoader;
import com.greenorange.vimusic.mvp.model.Music;

import java.util.List;

import rx.Observable;

/**
 * Created by guojin.hu on 2017/3/13.
 */

public class RepositoryImpl implements Repository {
    private Context mContext;
    private String mAction;

    public RepositoryImpl(Context context,String action) {
        mContext = context;
        mAction = action;
    }

    public void setAction(String action) {
        mAction = action;
    }


    // from network

    // from local
    @Override
    public Observable<List<Music>> getAllSongs() {
        switch (mAction) {
            case Constants.ACTION_NAVIGATE_ALL_SONGS :
                break;
            case Constants.ACTION_NAVIGATE_PLAYLIST_FAVORITE:
                break;
            case Constants.ACTION_NAVIGATE_PLAYLIST_RECENT_ADD:
                break;
            case Constants.ACTION_NAVIGATE_PLAYLIST_RECENT_PLAY:
                break;
        }
        return MusicLoader.getAllSongs(mContext);
    }

    public void refreshMusics(){

    }

}
