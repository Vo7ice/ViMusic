package com.greenorange.vimusic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.lang.ref.SoftReference;

/**
 * Created by guojin.hu on 2017/3/8.
 */

public class MediaPlayBackService extends Service {

    private final IBinder mBinder = new ServiceStub(this);

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

        }
    }
}
