package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.TypePageControllerListener;

public class TypePageController implements android.view.View.OnClickListener {
    private View View;
    private TypePageControllerListener listener;




    public TypePageController(View View, TypePageControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm:
                listener.submit();
                break;
            case R.id.reset:
                break;
            case R.id.handin:
//                listener.submit();
                break;
        }
    }
}