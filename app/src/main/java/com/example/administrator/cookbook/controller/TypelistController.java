package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.TypelistControllerListener;

public class TypelistController implements View.OnClickListener {
    private View View;
    private TypelistControllerListener listener;



    public TypelistController(View View, TypelistControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
