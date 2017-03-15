package com.greenorange.vimusic.mvp.presenter;

import com.greenorange.vimusic.mvp.contact.MusicContact;
import com.greenorange.vimusic.repository.RepositoryImpl;
import com.greenorange.vimusic.util.MusicLogUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by guojin.hu on 2017/3/14.
 */

public class MusicPresenter implements MusicContact.Presenter {

    private final RepositoryImpl mRepository;
    private CompositeSubscription mCompositeSubscription;
    private MusicContact.View mView;

    private boolean mFirstLoad = true;

    public MusicPresenter(RepositoryImpl repository) {
        mRepository = repository;
    }

    @Override
    public void attachView(MusicContact.View view) {
        mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        loadMusics(false);
    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void loadMusics(boolean forceUpdate) {
        loadMusics(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadMusics(boolean forceUpdate, boolean showLoadingUI) {
        if (showLoadingUI) {
            mView.showLoadingIndicator(true);
        }

        if (forceUpdate) {
            mRepository.refreshMusics();
        }

        mCompositeSubscription.clear();
        Subscription subscription = mRepository.getAllSongs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(musics -> {
                    if (null == musics || musics.isEmpty()) {
                        mView.showEmptyView();
                    } else{
                        mView.updateUI(musics);
                    }
                    MusicLogUtils.d("Vo7ice","size:"+musics.size());
                });
        mCompositeSubscription.add(subscription);
    }
}
