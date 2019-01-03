package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.CommentpageController;
import com.example.administrator.cookbook.model.Comment;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.model.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class CommentpageActivity extends AppCompatActivity implements CommentpageControllerListener{
    private String cookbook_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commentpage);
        Intent intent = getIntent();
        cookbook_id = intent.getStringExtra("cookbook_id");
        CommentpageController Controller = new CommentpageController(this.findViewById(R.id.commentpage), this);
        setListeners(Controller);
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.cookbook_submit).setOnClickListener(onClickListener);
    }
    @Override
    public void add(){
        final EditText comment=(EditText)findViewById(R.id.cookbook_comment);
        if(comment.getText().equals("null")){
            Toast.makeText(CommentpageActivity.this, "评论不能为空", Toast.LENGTH_SHORT).show();
        }
        else{
            BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
            bmobQuery.getObject(cookbook_id, new QueryListener<Cookbook>() {
                @Override
                public void done(final Cookbook cookbook, BmobException e) {
                    if (e == null) {
                        Comment p2 = new Comment();
                        p2.setCm_content(comment.getText().toString());
                        p2.setCookbook(cookbook);
                        p2.setAuthor(BmobUser.getCurrentUser(User.class));
                        p2.save(new SaveListener<String>() {
                            @Override
                            public void done(String objectId,BmobException e) {
                                if(e==null){
                                    Toast.makeText(CommentpageActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(CommentpageActivity.this, "不能重复评论", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Log.i("bmob", "失败：" + e.getMessage());
                    }
                }
            });
        }
    }
}
