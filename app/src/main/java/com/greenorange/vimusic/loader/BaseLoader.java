package com.greenorange.vimusic.loader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IntDef;

import com.greenorange.vimusic.Constants;
import com.greenorange.vimusic.util.MusicUtils;

/**
 * Created by guojin.hu on 2017/3/13.
 */

public abstract class BaseLoader {

    static String[] audiocols = new String[]{
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.MIME_TYPE,
            MediaStore.Audio.Media.YEAR
    };


    @IntDef({ALL, MUSIC, PODCAST})
    public @interface Category {
    }

    public static final int ALL = 0;
    public static final int MUSIC = 1;
    public static final int PODCAST = 2;

    private static int sCategory = ALL;

    public void setCategory(@Category int category) {
        sCategory = category;
    }

    protected static Cursor makeBaseCursor(Context context, Uri uri, String selection, String[] paramArrayOfString, String sortOrder) {
        Cursor cursor = null;
        switch (sCategory) {
            case ALL:
                cursor = MusicUtils.query(context, uri, audiocols, selection, paramArrayOfString, sortOrder);
                break;
            case MUSIC:
                cursor = MusicUtils.query(context, uri, audiocols, selection + Constants.MUSIC_ONLY_SELECTION, paramArrayOfString, sortOrder);
                break;
            case PODCAST:
                cursor = MusicUtils.query(context, uri, audiocols, selection + Constants.PODCAST_ONLY_SELECTION, paramArrayOfString, sortOrder);
                break;
        }
        return cursor;
    }

}
