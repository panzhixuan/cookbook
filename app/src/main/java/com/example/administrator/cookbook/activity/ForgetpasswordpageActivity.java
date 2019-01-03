package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.ForgetpasswordpageController;
import com.example.administrator.cookbook.model.User;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

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
        final EditText username=(EditText)findViewById(R.id.username);
        BmobQuery<User> Query = new BmobQuery<>();
        Query.addWhereEqualTo("username", username.getText().toString());
        Query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if (e == null) {
                    BmobUser.resetPasswordByEmail(object.get(0).getUsername(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e == null){
                                Toast.makeText(ForgetpasswordpageActivity.this, "重置密码请求成功，请到邮箱进行重置密码操作", Toast.LENGTH_SHORT).show();
                                ForgetpasswordpageActivity.this.finish();
                            }
                            else{
                                Toast.makeText(ForgetpasswordpageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(ForgetpasswordpageActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //ForgetpasswordpageActivity.this.finish();
    }
}
