package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.ForgetpasswordpageController;
import com.example.administrator.cookbook.view.ForgetpasswordpageView;

public class ForgetpasswordpageActivity extends AppCompatActivity implements ForgetpasswordpageControllerListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpasswordpage);
        ForgetpasswordpageController Controller = new ForgetpasswordpageController((ForgetpasswordpageView) this.findViewById(R.id.forgetpasswordpage), this);
        ((ForgetpasswordpageView) this.findViewById(R.id.forgetpasswordpage)).setListeners(Controller);
//        Button handin=(Button)findViewById(R.id.handin);
//        handin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ForgetpasswordpageActivity.this.finish();
//            }
//        });
    }
    @Override
    public void submit() {
        ForgetpasswordpageActivity.this.finish();
    }
}
