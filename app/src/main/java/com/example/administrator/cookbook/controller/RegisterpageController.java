package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.ForgetpasswordpageControllerListener;
import com.example.administrator.cookbook.activity.RegisterpageControllerListener;
import com.example.administrator.cookbook.view.ForgetpasswordpageView;
import com.example.administrator.cookbook.view.RegisterpageView;

public class RegisterpageController implements View.OnClickListener {
    private RegisterpageView View;
    private RegisterpageControllerListener listener;



    public RegisterpageController(RegisterpageView View, RegisterpageControllerListener listener) {

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
