package com.greenorange.vimusic.repository;

import com.greenorange.vimusic.mvp.model.Music;

import java.util.List;

import rx.Observable;

/**
 * Created by guojin.hu on 2017/3/13.
 */

public interface Repository {

    Observable<List<Music>> getAllSongs();

    
}
