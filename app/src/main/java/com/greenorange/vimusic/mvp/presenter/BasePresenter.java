package com.greenorange.vimusic.mvp.presenter;

import com.greenorange.vimusic.mvp.view.BaseView;

/**
 * Created by guojin.hu on 2017/3/6.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void subscribe();
    
    void unSubscribe();
}
