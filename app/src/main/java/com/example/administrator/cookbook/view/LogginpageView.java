package com.example.administrator.cookbook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.cookbook.R;

import java.util.jar.Attributes;

public class LogginpageView extends LinearLayout {
    public LogginpageView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.confirm).setOnClickListener(onClickListener);
        findViewById(R.id.forgetpassword).setOnClickListener(onClickListener);
        findViewById(R.id.register).setOnClickListener(onClickListener);
    }
}
