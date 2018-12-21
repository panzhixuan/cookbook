package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.RegisterpageControllerListener;

public class RegisterpageController implements View.OnClickListener {
    private View View;
    private RegisterpageControllerListener listener;



    public RegisterpageController(View View, RegisterpageControllerListener listener) {

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
