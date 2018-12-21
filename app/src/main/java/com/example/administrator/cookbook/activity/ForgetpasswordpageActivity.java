package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.ForgetpasswordpageController;

public class ForgetpasswordpageActivity extends AppCompatActivity implements ForgetpasswordpageControllerListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpasswordpage);
        ForgetpasswordpageController Controller = new ForgetpasswordpageController(this.findViewById(R.id.forgetpasswordpage), this);
        setListeners(Controller);
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.handin).setOnClickListener(onClickListener);
    }
    @Override
    public void submit() {
        ForgetpasswordpageActivity.this.finish();
    }
}
