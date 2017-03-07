package com.greenorange.vimusic.mvp.presenter;

import android.view.MenuItem;

import com.greenorange.vimusic.R;
import com.greenorange.vimusic.mvp.contact.MainContact;

/**
 * Created by guojin.hu on 2017/3/6.
 */

public class MainPresenter implements MainContact.Presenter {

    private int mCurrentTagId = -1;
    private MainContact.View mView;

    @Override
    public void attachView(MainContact.View view) {
        mView = view;
        if (mCurrentTagId == -1) {
            mView.navigateToLibrary();
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void managerFragment(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_library:
                mView.navigateToLibrary();
                break;
            case R.id.nav_list:
                mView.navigateToPlayList();
                break;
            case R.id.nav_folder:
                mView.navigateToFolders();
                break;
            case R.id.nav_favorite:
                mView.navigateToFavorite();
                break;
            case R.id.nav_recent_play:
                mView.navigateToRecentPlay();
                break;
            case R.id.nav_recent_add:
                mView.navigateToRecentAdd();
                break;
            case R.id.nav_settings:
                mView.navigateToSettings();
                break;
            case R.id.nav_exit:
                mView.exit();
                break;
        }
        mCurrentTagId = id;
        item.setChecked(true);
        mView.closeDrawerIfNeed();
    }
}
