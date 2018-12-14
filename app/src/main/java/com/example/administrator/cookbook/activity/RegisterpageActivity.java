package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.RegisterpageController;
import com.example.administrator.cookbook.view.RegisterpageView;

public class RegisterpageActivity extends AppCompatActivity implements RegisterpageControllerListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        RegisterpageController Controller = new RegisterpageController((RegisterpageView) this.findViewById(R.id.registerpage), this);
        ((RegisterpageView) this.findViewById(R.id.registerpage)).setListeners(Controller);
    }
    @Override
    public void submit() {
        RegisterpageActivity.this.finish();
    }
}
