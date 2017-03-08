package com.greenorange.vimusic.util;

import android.content.Context;
import android.content.Intent;

import com.greenorange.vimusic.Constants;

/**
 * Created by guojin.hu on 2017/3/6.
 */

public class NavigationUtils {


    public static void navigateToSettings(Context context) {
        final Intent intent = new Intent();
        intent.setAction(Constants.ACTION_NAVIGATE_SETTINGS);
        context.startActivity(intent);
    }

}
