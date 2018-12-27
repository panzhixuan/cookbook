package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.UserpageControllerListener;

public class UserPageController implements android.view.View.OnClickListener {
    private View View;
    private UserpageControllerListener listener;

    public UserPageController(View View, UserpageControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.headshot:
                break;
            case R.id.confirm3:
                break;
            case R.id.confirm2:
                break;
            case R.id.confirm1:
                break;
            case R.id.my_view:
                listener.toBrowserpage();
                break;
            case R.id.my_caipu:
                listener.toMycookbook();
                break;
            case R.id.my_collection:
                listener.toCollectpage();
                break;
            case R.id.logout:
                listener.logout();
                break;
        }
    }
}