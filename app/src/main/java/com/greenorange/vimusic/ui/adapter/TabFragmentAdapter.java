package com.greenorange.vimusic.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.greenorange.vimusic.ui.fragment.PagerFragment;

import java.util.List;

/**
 * Created by guojin.hu on 2017/3/7.
 */

public class TabFragmentAdapter extends FragmentPagerAdapter {

    private List<PagerFragment> mPagerFragments;
    private List<String> mFragmentTitles;

    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabFragmentAdapter(FragmentManager fm, List<PagerFragment> pagerFragments, List<String> fragmentTitles) {
        super(fm);
        mPagerFragments = pagerFragments;
        mFragmentTitles = fragmentTitles;
    }

    public void setData(List<PagerFragment> fragments, List<String> fragmentTitles) {
        mPagerFragments = fragments;
        mFragmentTitles = fragmentTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mPagerFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    @Override
    public int getCount() {
        return mPagerFragments.size();
    }

    public void clear() {
        mPagerFragments.clear();
        mFragmentTitles.clear();
    }

    public void replaceData(List<PagerFragment> fragments, List<String> fragmentTitles) {
        mPagerFragments = fragments;
        mFragmentTitles = fragmentTitles;
        notifyDataSetChanged();
    }
}
