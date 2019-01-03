package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.LogginpageController;
import com.example.administrator.cookbook.model.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LogginpageActivity extends AppCompatActivity implements LogginpageControllerListener{
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logginpage);
        Bmob.initialize(this, "74fe9d538d9cbf346227c96aaea494d2");
        LogginpageController loginController = new LogginpageController(this.findViewById(R.id.logginpage), this);
        setListeners(loginController);
        EditText password=(EditText) findViewById(R.id.password);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        if(BmobUser.getCurrentUser()!=null){
            Intent intent=null;
            intent=new Intent(LogginpageActivity.this,MainpageActivity.class);
            finish();
            startActivity(intent);
        }
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.confirm).setOnClickListener(onClickListener);
        findViewById(R.id.forgetpassword).setOnClickListener(onClickListener);
        findViewById(R.id.register).setOnClickListener(onClickListener);
    }
    @Override
    public void onLoginSuccess() {
        User user=new User();
        EditText username=(EditText)findViewById(R.id.username);
        EditText password=(EditText)findViewById(R.id.password);
        user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e){
                if(e==null){
                    User user= BmobUser.getCurrentUser(User.class);
                    //Snackbar.make(view,"登录成功"+user.getUsername(),Snackbar.LENGTH_LONG).show();
                    Intent intent=null;
                    intent=new Intent(LogginpageActivity.this,MainpageActivity.class);
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(LogginpageActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
