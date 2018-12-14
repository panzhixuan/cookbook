package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.ForgetpasswordpageControllerListener;
import com.example.administrator.cookbook.activity.LogginpageControllerListener;
import com.example.administrator.cookbook.view.ForgetpasswordpageView;
import com.example.administrator.cookbook.view.LogginpageView;

public class ForgetpasswordpageController implements View.OnClickListener{
    private ForgetpasswordpageView View;
    private ForgetpasswordpageControllerListener listener;



    public ForgetpasswordpageController(ForgetpasswordpageView View, ForgetpasswordpageControllerListener listener) {

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
