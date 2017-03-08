package com.greenorange.vimusic.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;

import com.greenorange.vimusic.IMediaPlayBackService;
import com.greenorange.vimusic.MediaPlayBackService;

import java.util.HashMap;

/**
 * Created by guojin.hu on 2017/3/8.
 */

public class MusicUtils {

    private static final String TAG = "MusicUtil";

    public static IMediaPlayBackService sService = null;
    private static HashMap<Context, ServiceBinder> sConnectionMap =
            new HashMap<Context, ServiceBinder>();

    /**
     * service token class.
     */
    public static class ServiceToken {
        ContextWrapper mWrappedContext;
        ServiceToken(ContextWrapper context) {
            mWrappedContext = context;
        }
    }

    /**
     * Bind the media playback service.
     *
     * @param context the context
     *
     * @return the service token
     */
    public static ServiceToken bindToService(Activity context) {
        return bindToService(context, null);
    }

    /**
     * Bind the media playback service.
     *
     * @param context the context
     * @param callback the connection callback
     *
     * @return the service token
     */
    public static ServiceToken bindToService(Activity context, ServiceConnection callback) {
        Activity realActivity = context.getParent();
        if (realActivity == null) {
            realActivity = context;
        }
        MusicLogUtils.d(TAG, "bindToService: activity=" + context.toString());
        ContextWrapper cw = new ContextWrapper(realActivity);
        cw.startService(new Intent(cw, MediaPlayBackService.class));
        ServiceBinder sb = new ServiceBinder(callback);
        if (cw.bindService((new Intent()).setClass(cw, MediaPlayBackService.class), sb, 0)) {
            sConnectionMap.put(cw, sb);
            return new ServiceToken(cw);
        }
        MusicLogUtils.e(TAG, "Failed to bind to service");
        return null;
    }

    /**
     * Class to connect the media playback service.
     */
    private static class ServiceBinder implements ServiceConnection {
        
        ServiceConnection mCallback;

        ServiceBinder(ServiceConnection callback) {
            mCallback = callback;
        }

        public void onServiceConnected(ComponentName className, android.os.IBinder service) {
            sService = IMediaPlayBackService.Stub.asInterface(service);
            // initAlbumArtCache();
            if (mCallback != null) {
                mCallback.onServiceConnected(className, service);
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            if (mCallback != null) {
                mCallback.onServiceDisconnected(className);
            }
            sService = null;
        }
    }
}
