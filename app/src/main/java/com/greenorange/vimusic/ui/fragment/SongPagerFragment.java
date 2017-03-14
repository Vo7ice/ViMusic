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
import com.greenorange.vimusic.mvp.contact.MusicContact;
import com.greenorange.vimusic.mvp.presenter.MusicPresenter;
import com.greenorange.vimusic.repository.RepositoryImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guojin.hu on 2017/3/8.
 */

public class SongPagerFragment extends PagerFragment implements MusicContact.View {


    @BindView(R.id.image_empty)
    ImageView mImageEmpty;
    @BindView(R.id.text_empty_title)
    TextView mTextEmptyTitle;
    @BindView(R.id.view_empty)
    RelativeLayout mViewEmpty;
    private String mAction;
    private MusicContact.Presenter mPresenter;

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
    public void onResume() {
        super.onResume();
        RepositoryImpl repository = new RepositoryImpl(getContext(), mAction);
        mPresenter = new MusicPresenter(repository);
        mPresenter.attachView(this);
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all_songs;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void setPresenter(MusicContact.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean force) {

    }

    @Override
    public void showEmptyView() {

    }
}
