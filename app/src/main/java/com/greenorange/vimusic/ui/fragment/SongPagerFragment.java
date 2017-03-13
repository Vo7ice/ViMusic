package com.greenorange.vimusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greenorange.vimusic.Constants;
import com.greenorange.vimusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guojin.hu on 2017/3/8.
 */

public class SongPagerFragment extends PagerFragment {


    @BindView(R.id.image_empty)
    ImageView mImageEmpty;
    @BindView(R.id.text_empty_title)
    TextView mTextEmptyTitle;
    @BindView(R.id.view_empty)
    RelativeLayout mViewEmpty;
    private String mAction;

    public static PagerFragment newInstance(String action) {
        SongPagerFragment fragment = new SongPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.NAVIGATE_PLAYLIST_TYPE, action);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAction = getArguments().getString(Constants.NAVIGATE_PLAYLIST_TYPE);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all_songs;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
