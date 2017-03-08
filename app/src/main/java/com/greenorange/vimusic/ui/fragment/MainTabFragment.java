package com.greenorange.vimusic.ui.fragment;

import android.os.Bundle;

import com.greenorange.vimusic.Constants;
import com.greenorange.vimusic.R;
import com.greenorange.vimusic.ui.adapter.TabFragmentAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guojin.hu on 2017/3/7.
 */

public class MainTabFragment extends TabFragment {

    private TabFragmentAdapter mTabFragmentAdapter;
    private List<PagerFragment> mPagerFragments;
    private List<String> mFragmentTitles;

    @Override
    public void initData() {
        mFragmentTitles = new ArrayList<>();
        mPagerFragments = new ArrayList<>();
        mTabFragmentAdapter = new TabFragmentAdapter(getChildFragmentManager(), mPagerFragments, mFragmentTitles);
        String[] stringArray = getActivity().getResources().getStringArray(R.array.tab_titles_array);
        mFragmentTitles.addAll(Arrays.asList(stringArray));
        mPagerFragments.add(SongPagerFragment.newInstance(mAction));
        mPagerFragments.add(AlbumPagerFragment.newInstance(mAction));
        mPagerFragments.add(ArtistPagerFragment.newInstance(mAction));
        mTabFragmentAdapter.replaceData(mPagerFragments, mFragmentTitles);
    }

    @Override
    public void initListener() {
        mViewpager.setAdapter(mTabFragmentAdapter);
        mTabs.setupWithViewPager(mViewpager);
    }

    public static MainTabFragment newInstance(String action) {
        MainTabFragment fragment = new MainTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.NAVIGATE_PLAYLIST_TYPE, action);
        fragment.setArguments(bundle);
        return fragment;
    }
}
