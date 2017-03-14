package com.greenorange.vimusic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import java.lang.ref.SoftReference;

/**
 * Created by guojin.hu on 2017/3/8.
 */

public class MediaPlayBackService extends Service {

    private final IBinder mBinder = new ServiceStub(this);

    String[] mCursorCols = new String[] {
            "audio._id AS _id",             // index must match IDCOLIDX below
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    static class ServiceStub extends IMediaPlayBackService.Stub {

        SoftReference<MediaPlayBackService> mService;

        ServiceStub(MediaPlayBackService service) {
            mService = new SoftReference<MediaPlayBackService>(service);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void openFile(String path) throws RemoteException {
            mService.get().open(path);
        }

        @Override
        public long getAlbumId() throws RemoteException {
            return 0;
        }

        @Override
        public long getArtistId() throws RemoteException {
            return 0;
        }

        @Override
        public long getAudioId() throws RemoteException {
            return 0;
        }

        @Override
        public int getShuffleMode() throws RemoteException {
            return 0;
        }
    }

    public void open(String path) {
        
    }
}
