package com.greenorange.vimusic.mvp.model;

/**
 * Created by guojin.hu on 2017/3/13.
 */

public class Music {
    private long id;
    private String artist;
    private String album;
    private String title;
    private String path;
    private String type;
    private long albumId;
    private long artistId;
    private int is_podcast;
    private int bookmark;
    private int duration;

    public Music(long id, String artist, String album, String title, String path, String type, long albumId, long artistId, int is_podcast, int bookmark, int duration) {
        this.id = id;
        this.artist = artist;
        this.album = album;
        this.title = title;
        this.path = path;
        this.type = type;
        this.albumId = albumId;
        this.artistId = artistId;
        this.is_podcast = is_podcast;
        this.bookmark = bookmark;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public int getIs_podcast() {
        return is_podcast;
    }

    public void setIs_podcast(int is_podcast) {
        this.is_podcast = is_podcast;
    }

    public int getBookmark() {
        return bookmark;
    }

    public void setBookmark(int bookmark) {
        this.bookmark = bookmark;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Music() {
    }
}
