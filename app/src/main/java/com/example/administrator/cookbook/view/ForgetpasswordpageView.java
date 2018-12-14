package com.example.administrator.cookbook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.cookbook.R;

public class ForgetpasswordpageView extends LinearLayout {
    public ForgetpasswordpageView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.handin).setOnClickListener(onClickListener);
    }
}