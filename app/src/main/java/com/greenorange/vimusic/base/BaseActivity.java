package com.greenorange.vimusic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by guojin.hu on 2017/3/6.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private String mAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mAction = getIntent().getAction();
        super.onCreate(savedInstanceState);
        setContentView(loadResId());
        ButterKnife.bind(this);
    }

    protected abstract int loadResId();

    public String getAction() {
        return mAction;
    }



}
