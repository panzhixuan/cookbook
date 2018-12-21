package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.activity.MycookbooklistControllerListener;

public class MycookbooklistController implements View.OnClickListener {
    private android.view.View View;
    private MycookbooklistControllerListener listener;



    public MycookbooklistController(android.view.View View, MycookbooklistControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {

    }
}
