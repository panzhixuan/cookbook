package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.MycookbookControllerListener;

public class MycookbookController implements View.OnClickListener{
    private android.view.View View;
    private MycookbookControllerListener listener;



    public MycookbookController(android.view.View View, MycookbookControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.create:
                listener.toCreatecookbook();
                break;
        }
    }
}
