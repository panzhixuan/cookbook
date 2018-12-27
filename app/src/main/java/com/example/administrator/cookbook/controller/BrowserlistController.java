package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.BrowserlistControllerListener;

public class BrowserlistController implements View.OnClickListener{
    private android.view.View View;
    private BrowserlistControllerListener listener;



    public BrowserlistController(View View, BrowserlistControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.browselist:
                listener.tocookbookdetail();
                break;
        }
    }
}
