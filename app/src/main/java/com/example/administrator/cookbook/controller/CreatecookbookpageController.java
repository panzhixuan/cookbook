package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.CreatecookbookpageControllerListener;

public class CreatecookbookpageController implements View.OnClickListener {
    private View View;
    private CreatecookbookpageControllerListener listener;



    public CreatecookbookpageController(View View, CreatecookbookpageControllerListener listener) {

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
                listener.addcover();
                break;
            case R.id.create_cookbook:
                listener.create();
                break;
        }
    }
}
