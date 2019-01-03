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
                listener.changheadslot();
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
            case R.id.edit:
                listener.edit();
                break;
            case R.id.caishi:
                listener.caishi();
                break;
            case R.id.caixi:
                listener.caixi();
                break;
            case R.id.changhe:
                listener.changhe();
                break;
        }
    }
}