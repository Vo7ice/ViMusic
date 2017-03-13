package com.greenorange.vimusic;

import android.provider.MediaStore;

/**
 * Created by guojin.hu on 2017/3/6.
 */

public class Constants {

    public static final String ACTION_NAVIGATE_SETTINGS = "vimusic:settings";
    public static final String ACTION_NAVIGATE_SEARCH = "vimusic:search";

    public static final String ACTION_NAVIGATE_ALL_SONGS = "vimusic:all_songs";
    public static final String ACTION_NAVIGATE_PLAYLIST_FAVORITE = "vimusic:favorite";
    public static final String ACTION_NAVIGATE_PLAYLIST_RECENT_PLAY = "vimusic:recent_play";
    public static final String ACTION_NAVIGATE_PLAYLIST_RECENT_ADD = "vimusic:recent_add";

    public static final String NAVIGATE_PLAYLIST_TYPE = "playlist_type";

    public static final String MUSIC_ONLY_SELECTION = MediaStore.Audio.Media.IS_MUSIC + "=1";
    public static final String PODCAST_ONLY_SELECTION = MediaStore.Audio.Media.IS_PODCAST + "=1";
}
