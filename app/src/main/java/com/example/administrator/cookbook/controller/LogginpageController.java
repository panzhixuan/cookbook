package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.LogginpageControllerListener;
import com.example.administrator.cookbook.view.LogginpageView;

public class LogginpageController implements View.OnClickListener {

    private LogginpageView loginView;
    private LogginpageControllerListener listener;



    public LogginpageController(LogginpageView loginView, LogginpageControllerListener listener) {

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
