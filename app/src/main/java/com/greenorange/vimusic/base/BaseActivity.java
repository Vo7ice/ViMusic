package com.greenorange.vimusic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.greenorange.vimusic.R;

import butterknife.ButterKnife;

/**
 * Created by guojin.hu on 2017/3/6.
 */

public abstract class BaseActivity extends AppCompatActivity implements IInit {


    private String mAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mAction = getIntent().getAction();
        super.onCreate(savedInstanceState);
        setContentView(loadResId());
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initListener();
    }

    protected abstract int loadResId();

    public String getAction() {
        return mAction;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.action_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
