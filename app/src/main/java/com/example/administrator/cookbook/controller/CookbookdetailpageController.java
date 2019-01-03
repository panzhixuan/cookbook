package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.CookbookdetailpageControllerListener;

public class CookbookdetailpageController implements View.OnClickListener {
    private View View;
    private CookbookdetailpageControllerListener listener;



    public CookbookdetailpageController(View View, CookbookdetailpageControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.favor:
                listener.favorite();
                break;
            case R.id.publish:
                listener.addcomment();
                break;
        }
    }
}
