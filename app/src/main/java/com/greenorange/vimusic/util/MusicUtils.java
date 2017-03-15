package com.greenorange.vimusic.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.MediaStore;

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
    private static String sLastSdStatus = null;

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
     * Unbind the media playback service.
     *
     * @param token the service token.
     */
    public static void unbindFromService(ServiceToken token) {
        /// M: set mLastSdStatus is null when unbind service
        sLastSdStatus = null;
        MusicLogUtils.v(TAG, "Reset mLastSdStatus to be null");
        if (token == null) {
            MusicLogUtils.e(TAG, "Trying to unbind with null token");
            return;
        }
        ContextWrapper cw = token.mWrappedContext;
        ServiceBinder sb = sConnectionMap.remove(cw);
        if (sb == null) {
            MusicLogUtils.e(TAG, "Trying to unbind for unknown Context");
            return;
        }
        cw.unbindService(sb);
        if (sConnectionMap.isEmpty()) {
            // presumably there is nobody interested in the service at this point,
            // so don't hang on to the ServiceConnection
            sService = null;
        }
    }

    /**
     * Get current album id.
     *
     * @return the current album id
     */
    public static long getCurrentAlbumId() {
        if (sService != null) {
            try {
                return sService.getAlbumId();
            } catch (RemoteException ex) {
                MusicLogUtils.e(TAG, "Exception:" + ex);
            }
        }
        return -1;
    }

    /**
     * Get current artist id.
     *
     * @return the current artist id
     */
    public static long getCurrentArtistId() {
        if (MusicUtils.sService != null) {
            try {
                return sService.getArtistId();
            } catch (RemoteException ex) {
                MusicLogUtils.e(TAG, "Exception:" + ex);
            }
        }
        return -1;
    }

    /**
     * Get current audio id.
     *
     * @return the current audio id
     */
    public static long getCurrentAudioId() {
        if (MusicUtils.sService != null) {
            try {
                return sService.getAudioId();
            } catch (RemoteException ex) {
                MusicLogUtils.e(TAG, "Exception:" + ex);
            }
        }
        return -1;
    }

    /**
     * Get current shuffle mode.
     *
     * @return the current shuffle mode.
     */
    public static int getCurrentShuffleMode() {
        int mode = 0;/* = MediaPlaybackService.SHUFFLE_NONE*/
        if (sService != null) {
            try {
                mode = sService.getShuffleMode();
            } catch (RemoteException ex) {
                MusicLogUtils.e(TAG, "Exception:" + ex);
            }
        }
        return mode;
    }

    /**
     * Utils to query database.
     *
     * @param context the context
     * @param uri     the URI
     * @param projection the projection
     * @param selection the selection string
     * @param selectionArgs the argument of the selection
     * @param sortOrder the sort order for the output cusor
     * @param limit the limit of this query
     *
     * @return the cursor
     */
    public static Cursor query(Context context, Uri uri, String[] projection,
                               String selection, String[] selectionArgs, String sortOrder, int limit) {
        try {
            ContentResolver resolver = context.getContentResolver();
            if (resolver == null) {
                return null;
            }
            if (limit > 0) {
                uri = uri.buildUpon().appendQueryParameter("limit", "" + limit).build();
            }
            return resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        } catch (UnsupportedOperationException ex) {
            return null;
        }

    }

    /**
     * Utils to query database.
     *
     * @param context the context
     * @param uri     the URI
     * @param projection the projection
     * @param selection the selection string
     * @param selectionArgs the argument of the selection
     * @param sortOrder the sort order for the output cusor
     *
     * @return the cursor
     */
    public static Cursor query(Context context, Uri uri, String[] projection,
                               String selection, String[] selectionArgs, String sortOrder) {
        return query(context, uri, projection, selection, selectionArgs, sortOrder, 0);
    }

    /**
     *
     * @param context context
     * @param name playlist name
     * @return playlist id with given name if exist, otherwise -1.
     */
    public static int idForplaylist(Context context, String name) {
        Cursor c = MusicUtils.query(context, MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Playlists._ID, MediaStore.Audio.Playlists.NAME},
                /*MediaStore.Audio.Playlists.NAME + "=?"*/null,
                /*new String[] { name }*/null,
                MediaStore.Audio.Playlists.NAME);
        int id = -1;
        if (c == null) {
            return id;
        }
        c.moveToFirst();
        while (! c.isAfterLast()) {
            String playlistname = c.getString(1);
            if (playlistname != null && playlistname.compareToIgnoreCase(name) == 0) {
                id = c.getInt(0);
                break;
            }
            c.moveToNext();
        }
        c.close();
        return id;
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
