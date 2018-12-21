package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.ForgetpasswordpageControllerListener;

public class ForgetpasswordpageController implements View.OnClickListener{
    private View View;
    private ForgetpasswordpageControllerListener listener;



    public ForgetpasswordpageController(View View, ForgetpasswordpageControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.handin:
                listener.submit();
                break;
        }
    }
}
