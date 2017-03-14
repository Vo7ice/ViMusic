// IMediaPlayBackService.aidl
package com.greenorange.vimusic;

// Declare any non-default types here with import statements
import android.graphics.Bitmap;

interface IMediaPlayBackService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void openFile(String path);
    long getAlbumId();
    long getArtistId();
    long getAudioId();
    int getShuffleMode();
}
