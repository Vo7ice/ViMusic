package com.greenorange.vimusic.repository;

import com.greenorange.vimusic.loader.MusicLoader;
import com.greenorange.vimusic.mvp.model.Song;

import java.util.List;

import rx.Observable;

/**
 * Created by guojin.hu on 2017/3/13.
 */

public class RepositoryImpl implements Repository {

    public RepositoryImpl() {
    }

    // from network

    // from local
    @Override
    public Observable<List<Song>> getAllSongs() {
        return MusicLoader.;
    }

}
