package com.greenorange.vimusic;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.greenorange.vimusic.permission.PermissionManager;

/**
 * Created by guojin.hu on 2017/3/6.
 */

public class ViMusicApp extends Application {

    private static Context INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        PermissionManager.init(this);
    }

    public static Context getInstance() {
        return INSTANCE;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(INSTANCE).clearDiskCache();
        Glide.get(INSTANCE).clearMemory();
    }
}
