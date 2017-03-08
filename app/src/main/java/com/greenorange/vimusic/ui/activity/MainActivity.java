package com.greenorange.vimusic.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.greenorange.vimusic.Constants;
import com.greenorange.vimusic.R;
import com.greenorange.vimusic.RxBus;
import com.greenorange.vimusic.base.BaseActivity;
import com.greenorange.vimusic.event.ToolbarTitleEvent;
import com.greenorange.vimusic.mvp.contact.MainContact;
import com.greenorange.vimusic.mvp.presenter.MainPresenter;
import com.greenorange.vimusic.ui.fragment.MainTabFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.BindView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContact.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout mSlidingLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private MainContact.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int loadResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
            mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mPresenter.managerFragment(item);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe(this);
    }

    @Override
    public void initData() {
        mPresenter = new MainPresenter();
        setSupportActionBar(mToolbar);
        subscribeToolbarTitleEvent();
        mPresenter.attachView(this);
    }

    @Override
    public void initListener() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setPresenter(MainContact.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void closeDrawerIfNeed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void navigateToLibrary() {
        mNavView.getMenu().findItem(R.id.nav_library).setChecked(true);
        Fragment fragment = MainTabFragment.newInstance(Constants.ACTION_NAVIGATE_ALL_SONGS);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,Constants.ACTION_NAVIGATE_ALL_SONGS).commitAllowingStateLoss();
    }

    @Override
    public void navigateToPlayList() {

    }

    @Override
    public void navigateToFolders() {

    }

    @Override
    public void navigateToFavorite() {

    }

    @Override
    public void navigateToRecentPlay() {

    }

    @Override
    public void navigateToRecentAdd() {

    }

    @Override
    public void navigateToSettings() {

    }

    @Override
    public void exit() {

    }

    private void subscribeToolbarTitleEvent() {
        Subscription subscription = RxBus.getInstance()
                .toObservable(ToolbarTitleEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinctUntilChanged()
                .subscribe(new Action1<ToolbarTitleEvent>() {
                    @Override
                    public void call(ToolbarTitleEvent toolbarTitleEvent) {
                        mToolbar.setTitle(toolbarTitleEvent.getResId());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mToolbar.setTitle(R.string.toolbar_title_my_song);
                    }
                });
        RxBus.getInstance().addSubscription(this, subscription);
    }
}
