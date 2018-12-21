package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.LogginpageControllerListener;

public class LogginpageController implements View.OnClickListener {

    private View loginView;
    private LogginpageControllerListener listener;



    public LogginpageController(View loginView, LogginpageControllerListener listener) {

        this.loginView = loginView;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm:
                listener.onLoginSuccess();
                break;
            case R.id.forgetpassword:
                listener.forgetPassword();
                break;
            case R.id.register:
                listener.register();
                break;
        }
    }
}
