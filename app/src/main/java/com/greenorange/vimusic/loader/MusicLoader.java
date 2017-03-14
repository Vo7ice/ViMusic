package com.greenorange.vimusic.loader;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.greenorange.vimusic.Constants;
import com.greenorange.vimusic.mvp.model.Music;
import com.greenorange.vimusic.util.MusicUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by guojin.hu on 2017/3/13.
 */

public class MusicLoader {

    private static String[] sCursorCols = new String[]{
            MediaStore.Audio.Media._ID,             // index must match IDCOLIDX below
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.MIME_TYPE,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.IS_PODCAST, // index must match PODCASTCOLIDX below
            MediaStore.Audio.Media.BOOKMARK,    // index must match BOOKMARKCOLIDX below
            MediaStore.Audio.Media.DURATION,
    };


    private MusicLoader() {

    }

    public static Observable<List<Music>> getSongsFromCursor(final Cursor cursor) {
        return Observable.create(new Observable.OnSubscribe<List<Music>>() {
            @Override
            public void call(Subscriber<? super List<Music>> subscriber) {
                List<Music> arrayList = new ArrayList<Music>();
                if ((cursor != null) && (cursor.moveToFirst()))
                    do {
                        long id = cursor.getLong(0);
                        String artist = cursor.getString(1);
                        String album = cursor.getString(2);
                        String title = cursor.getString(3);
                        String path = cursor.getString(4);
                        String type = cursor.getString(5);
                        long albumId = cursor.getLong(6);
                        long artistId = cursor.getLong(7);
                        int is_podcast = cursor.getInt(8);
                        int bookmark = cursor.getInt(9);
                        int duration = cursor.getInt(10);

                        arrayList.add(new Music(id, artist, album, title, path, type, albumId, artistId, is_podcast, bookmark, duration));
                    }
                    while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                subscriber.onNext(arrayList);
                subscriber.onCompleted();
            }
        });
    }


    public static Observable<List<Music>> getAllSongs(Context context) {
        return getSongsFromCursor(makeMusicCursor(context, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER));
    }

    public static Cursor makeMusicCursor(Context context, String selection, String[] selectionArgs, String sortOrder) {
        StringBuilder sb;
        sb = new StringBuilder();
        if (TextUtils.isEmpty(selection)) {
            sb.append(Constants.MUSIC_ONLY_SELECTION);
        } else {
            sb.append(selection).append(" AND ").append(Constants.MUSIC_ONLY_SELECTION);
        }
        return MusicUtils.query(context, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                sCursorCols, sb.toString(), selectionArgs, sortOrder);
    }
}
