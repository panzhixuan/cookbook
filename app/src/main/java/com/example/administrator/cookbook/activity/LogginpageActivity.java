package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.LogginpageController;

public class LogginpageActivity extends AppCompatActivity implements LogginpageControllerListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logginpage);
        LogginpageController loginController = new LogginpageController(this.findViewById(R.id.logginpage), this);
        setListeners(loginController);
        EditText password=(EditText) findViewById(R.id.password);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.confirm).setOnClickListener(onClickListener);
        findViewById(R.id.forgetpassword).setOnClickListener(onClickListener);
        findViewById(R.id.register).setOnClickListener(onClickListener);
    }
    @Override
    public void onLoginSuccess() {
        Intent intent=null;
        intent=new Intent(LogginpageActivity.this,MainpageActivity.class);
        startActivity(intent);
    }
    @Override
    public void forgetPassword() {
        Intent intent=null;
        intent=new Intent(LogginpageActivity.this,ForgetpasswordpageActivity.class);
        startActivity(intent);
    }
    @Override
    public void register() {
        Intent intent=null;
        intent=new Intent(LogginpageActivity.this,RegisterpageActivity.class);
        startActivity(intent);
    }
}
