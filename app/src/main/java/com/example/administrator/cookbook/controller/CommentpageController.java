package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.CommentpageControllerListener;
import com.example.administrator.cookbook.activity.CookbookdetailpageControllerListener;

public class CommentpageController implements View.OnClickListener{
    private android.view.View View;
    private CommentpageControllerListener listener;



    public CommentpageController(View View, CommentpageControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cookbook_submit:
                listener.add();
                break;
        }
    }
}
