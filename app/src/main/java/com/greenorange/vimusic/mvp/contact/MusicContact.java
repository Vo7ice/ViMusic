package com.greenorange.vimusic.mvp.contact;

import com.greenorange.vimusic.mvp.model.Music;
import com.greenorange.vimusic.mvp.presenter.BasePresenter;
import com.greenorange.vimusic.mvp.view.BaseView;

import java.util.List;

/**
 * Created by guojin.hu on 2017/3/14.
 */

public class MusicContact {

    public interface Presenter extends BasePresenter<View> {
        void loadMusics(boolean forceUpdate);
    }

    public interface View extends BaseView<Presenter> {
        void showLoadingIndicator(boolean force);

        void showEmptyView();

        void updateUI(List<Music> musicList);
    }
}
