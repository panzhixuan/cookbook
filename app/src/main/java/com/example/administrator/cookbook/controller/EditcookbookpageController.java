package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.CreatecookbookpageControllerListener;
import com.example.administrator.cookbook.activity.EditcookbookpageControllerListener;

public class EditcookbookpageController implements View.OnClickListener{
    private android.view.View View;
    private EditcookbookpageControllerListener listener;



    public EditcookbookpageController(View View, EditcookbookpageControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.material_add:
                listener.addMItem();
                break;
            case R.id.add_step:
                listener.addSItem();
                break;
            case R.id.delete_step:
                listener.delSItem();
                break;
            case R.id.cover:
                listener.editcover();
                break;
            case R.id.create_cookbook:
                listener.create();
                break;
        }
    }
}
