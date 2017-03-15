package com.greenorange.vimusic.ui.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenorange.vimusic.R;
import com.greenorange.vimusic.mvp.model.Music;
import com.greenorange.vimusic.util.GlideUtils;

import java.util.List;

/**
 * Created by guojin.hu on 2017/3/13.
 */

public class MusicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String mUnknownArtist;
    private String mUnknownAlbum;
    private List<Music> mMusicList;
    private Context mContext;
    private boolean withHeader;
    private String mAction;
    private long[] musicIds;


    private static final int TYPE_SHUFFLE_MODE = 1;
    private static final int TYPE_MUSIC_CONTENT = 2;

    public MusicListAdapter(List<Music> musicList, Context context, boolean withHeader, String action) {
        mMusicList = musicList;
        mContext = context;
        this.withHeader = withHeader;
        musicIds = getMusicIds();
        mAction = action;
        mUnknownAlbum = context.getString(R.string.unknown_album_name);
        mUnknownArtist = context.getString(R.string.unknown_artist_name);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_SHUFFLE_MODE;
        } else {
            return TYPE_MUSIC_CONTENT;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view;
        switch (viewType) {
            case TYPE_SHUFFLE_MODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shuffle_mode, parent, false);
                holder = new ShuffleModeViewHolder(view);
                break;
            case TYPE_MUSIC_CONTENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_layout, parent, false);
                holder = new MusicContentViewHodler(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_SHUFFLE_MODE:
                break;
            case TYPE_MUSIC_CONTENT:
                MusicContentViewHodler viewHolder = (MusicContentViewHodler) holder;
                Music localItem;
                if (withHeader) {
                    localItem = mMusicList.get(position - 1);
                } else {
                    localItem = mMusicList.get(position);
                }
                viewHolder.title.setText(localItem.getTitle());
                String name = localItem.getAlbum();
                String displayName = name;
                if (TextUtils.equals(name, MediaStore.UNKNOWN_STRING)) {
                    displayName = mUnknownAlbum;
                }
                viewHolder.albumArt.setText(displayName);

                name = localItem.getArtist();
                displayName = name;
                if (TextUtils.equals(name, MediaStore.UNKNOWN_STRING)) {
                    displayName = mUnknownArtist;
                }
                viewHolder.artist.setText(displayName);
                GlideUtils.loadAlbumIcon(mContext, localItem.getId(), localItem.getAlbumId(), viewHolder.album);

                break;
        }
    }

    @Override
    public int getItemCount() {
        if (withHeader && !mMusicList.isEmpty()) {
            return mMusicList.size() + 1;
        } else {
            return mMusicList.size();
        }
    }

    public void replaceData(List<Music> musicList) {
        mMusicList = musicList;
        notifyDataSetChanged();
    }

    private long[] getMusicIds() {
        int length = mMusicList.size();
        long[] temp = new long[length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = mMusicList.get(i).getId();
        }
        return temp;
    }

    public class ShuffleModeViewHolder extends RecyclerView.ViewHolder {

        public ShuffleModeViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MusicContentViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView album;
        private TextView title, artist, albumArt;
        private ImageView popupMenu;

        public MusicContentViewHodler(View itemView) {
            super(itemView);
            album = (ImageView) itemView.findViewById(R.id.music_albrm);
            title = (TextView) itemView.findViewById(R.id.music_title);
            artist = (TextView) itemView.findViewById(R.id.music_subtitle1);
            albumArt = (TextView) itemView.findViewById(R.id.music_subtitle2);
            popupMenu = (ImageView) itemView.findViewById(R.id.music_popupmenu);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
