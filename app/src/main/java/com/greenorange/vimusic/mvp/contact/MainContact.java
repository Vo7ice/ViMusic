package com.greenorange.vimusic.mvp.contact;

import android.view.MenuItem;

import com.greenorange.vimusic.mvp.presenter.BasePresenter;
import com.greenorange.vimusic.mvp.view.BaseView;

/**
 * Created by guojin.hu on 2017/3/6.
 */

public class MainContact {
    public interface Presenter extends BasePresenter<View> {
        void managerFragment(MenuItem item);
    }

    public interface View extends BaseView<Presenter> {
        void navigateToSettings();

        void closeDrawerIfNeed();

        void navigateToLibrary();

        void navigateToPlayList();

        void navigateToFolders();

        void navigateToFavorite();

        void navigateToRecentPlay();

        void navigateToRecentAdd();

        void exit();
    }
}
