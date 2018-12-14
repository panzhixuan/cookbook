package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.MainpageControllerListener;
import com.example.administrator.cookbook.activity.RegisterpageControllerListener;
import com.example.administrator.cookbook.view.MainpageView;
import com.example.administrator.cookbook.view.RegisterpageView;

public class MainpageController implements View.OnClickListener {
    private MainpageView View;
    private MainpageControllerListener listener;



    public MainpageController(MainpageView View, MainpageControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.type1:
                listener.test();
                break;
        }
    }
}
