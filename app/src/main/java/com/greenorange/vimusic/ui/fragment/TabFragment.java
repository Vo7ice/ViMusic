package com.greenorange.vimusic.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenorange.vimusic.R;
import com.greenorange.vimusic.base.IInit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guojin.hu on 2017/3/7.
 */

public abstract class TabFragment extends Fragment implements IInit {


    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private View mRootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_tab, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        initListener();
    }

}
