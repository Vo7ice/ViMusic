package com.greenorange.vimusic.event;

/**
 * Created by guojin.hu on 2017/3/7.
 */

public class ToolbarTitleEvent {
    private int resId;
    private String title;

    public ToolbarTitleEvent() {
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ToolbarTitleEvent{" +
                "resId=" + resId +
                ", title='" + title + '\'' +
                '}';
    }
}
