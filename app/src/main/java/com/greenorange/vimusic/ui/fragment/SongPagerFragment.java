package com.greenorange.vimusic.ui.fragment;

import android.os.Bundle;

import com.greenorange.vimusic.Constants;
import com.greenorange.vimusic.R;

/**
 * Created by guojin.hu on 2017/3/8.
 */

public class SongPagerFragment extends PagerFragment {


    public static PagerFragment newInstance(String action) {
        SongPagerFragment fragment = new SongPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.NAVIGATE_PLAYLIST_TYPE,action);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_all_songs;
    }
}
