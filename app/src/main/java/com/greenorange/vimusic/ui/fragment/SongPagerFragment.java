package com.greenorange.vimusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greenorange.vimusic.Constants;
import com.greenorange.vimusic.R;
import com.greenorange.vimusic.mvp.contact.MusicContact;
import com.greenorange.vimusic.mvp.model.Music;
import com.greenorange.vimusic.mvp.presenter.MusicPresenter;
import com.greenorange.vimusic.repository.RepositoryImpl;
import com.greenorange.vimusic.ui.adapter.MusicListAdapter;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.swl_refresh)
    SwipeRefreshLayout mSwlRefresh;
    private String mAction;
    private MusicContact.Presenter mPresenter;
    private MusicListAdapter mMusicListAdapter;

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
        mMusicListAdapter = new MusicListAdapter(new ArrayList<>(),getContext(),true,mAction);
        mPresenter = new MusicPresenter(repository);
        mPresenter.attachView(this);
        mPresenter.subscribe();
        mRvContent.setAdapter(mMusicListAdapter);
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        // mRvContent.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
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
        if (force) {
            mSwlRefresh.setRefreshing(true);
        }
    }

    @Override
    public void showEmptyView() {
        mViewEmpty.setVisibility(View.VISIBLE);
        mRvContent.setVisibility(View.GONE);
    }

    @Override
    public void updateUI(List<Music> musicList) {
        Log.d("Vo7ice","fragment:"+musicList.size());
        mViewEmpty.setVisibility(View.GONE);
        mRvContent.setVisibility(View.VISIBLE);
        mSwlRefresh.setRefreshing(false);
        mMusicListAdapter.replaceData(musicList);
        mMusicListAdapter.notifyDataSetChanged();
    }
}
