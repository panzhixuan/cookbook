package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.CookbookdetailpageController;

public class CookbookdetailpageActivity extends AppCompatActivity implements CookbookdetailpageControllerListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cookbookdetailpage);
        CookbookdetailpageController Controller = new CookbookdetailpageController(this.findViewById(R.id.cookbookdetailpage), this);
        setListeners(Controller);
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.favor).setOnClickListener(onClickListener);
    }
    @Override
    public void favorite() {
        ImageView imageview=(ImageView)findViewById(R.id.favor);
        imageview.setImageResource(R.drawable.favo_filled);
    }
}
