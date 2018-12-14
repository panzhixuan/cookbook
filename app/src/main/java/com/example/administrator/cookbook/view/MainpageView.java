package com.example.administrator.cookbook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.cookbook.R;

public class MainpageView extends LinearLayout {
    public MainpageView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.type1).setOnClickListener(onClickListener);
    }
}
