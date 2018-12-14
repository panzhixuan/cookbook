package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.cookbook.R;

public class MainpageActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    BottomNavigationBar mBottomNavigationBar;
    FrameLayout mFrameLayout;
    private MainpageFragment mBookFragment;
    private TypeFragment mLikeFragment;
    private UserFragment mUserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationBar=(BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        mFrameLayout=(FrameLayout)findViewById(R.id.fragment_container);
        InitNavigationBar();
        setDefaultFragment();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        actIsAlive=true;
//        autoViewPager();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        actIsAlive=false;
//    }

    private void InitNavigationBar() {
        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.homefill, "首页").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.zoomin, "分类").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.userora, "用户").setActiveColorResource(R.color.orange))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mBookFragment = MainpageFragment.newInstance("1");
        transaction.replace(R.id.fragment_container, mBookFragment);
        transaction.commit();
    }
    @Override
    public void onTabSelected(int position) {
        Log.d("onTabSelected", "onTabSelected: " + position);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mBookFragment == null) {
                    mBookFragment = MainpageFragment.newInstance("1");
                }
                transaction.replace(R.id.fragment_container, mBookFragment);
                break;
            case 1:
                if (mLikeFragment == null) {
                    mLikeFragment = TypeFragment.newInstance("2");
                }
                transaction.replace(R.id.fragment_container, mLikeFragment);
                break;
            case 2:
                if (mUserFragment == null) {
                    mUserFragment = UserFragment.newInstance("3");
                }
                transaction.replace(R.id.fragment_container, mUserFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }
    @Override
    public void onTabUnselected(int position) {
        Log.d("onTabUnselected", "onTabUnselected: " + position);
    }

    @Override
    public void onTabReselected(int position) {
        Log.d("onTabReselected", "onTabReselected: " + position);
    }
}
