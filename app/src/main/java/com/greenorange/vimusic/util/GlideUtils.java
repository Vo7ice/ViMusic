package com.greenorange.vimusic.util;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.greenorange.vimusic.R;

/**
 * Created by guojin.hu on 2017/3/15.
 */

public class GlideUtils {
    private static final String TAG = "GlideUtils";

    private static final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

    private static Uri generateAlbumUri(long songId, long albumId) {
        MusicLogUtils.d(TAG, ">> generateAlbumUri, song_id=" + songId + ", album_id=" + albumId);
        Uri uri = null;
        if (albumId < 0) {
            // This is something that is not in the database, so get the album art directly
            // from the file.
            if (songId > 0) {
                uri = Uri.parse("content://media/external/audio/media/" + songId + "/albumart");
            }
        } else {
            uri = ContentUris.withAppendedId(sArtworkUri, albumId);
        }
        return uri;
    }

    public static void loadAlbumIcon(Context context, long songId, long albumId, final ImageView imageView) {
        Glide.with(context).load(generateAlbumUri(songId, albumId))
                .error(R.drawable.ic_album_unknow)
                .placeholder(R.drawable.ic_album_unknow)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }
}
