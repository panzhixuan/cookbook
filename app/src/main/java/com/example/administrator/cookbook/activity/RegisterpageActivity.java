package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.RegisterpageController;

public class RegisterpageActivity extends AppCompatActivity implements RegisterpageControllerListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        RegisterpageController Controller = new RegisterpageController(this.findViewById(R.id.registerpage), this);
        setListeners(Controller);
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.handin).setOnClickListener(onClickListener);
    }
    @Override
    public void submit() {
        RegisterpageActivity.this.finish();
    }
}
