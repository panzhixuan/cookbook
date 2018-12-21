package com.example.administrator.cookbook.controller;

import android.view.View;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.activity.MainpageControllerListener;

public class MainpageController implements View.OnClickListener {
    private View View;
    private MainpageControllerListener listener;



    public MainpageController(View View, MainpageControllerListener listener) {

        this.View = View;

        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.type1:
                listener.toTypeActivity("川菜");
                break;
            case R.id.type2:
                listener.toTypeActivity("淮扬");
                break;
            case R.id.type3:
                listener.toTypeActivity("徽菜");
                break;
            case R.id.type4:
                listener.toTypeActivity("鲁菜");
                break;
            case R.id.type5:
                listener.toTypeActivity("闽菜");
                break;
            case R.id.type6:
                listener.toTypeActivity("粤菜");
                break;
            case R.id.type7:
                listener.toTypeActivity("湘菜");
                break;
            case R.id.type8:
                listener.toTypeActivity("浙菜");
                break;
            case R.id.searchconfirm:
                listener.toSearchActivity();
                break;
        }
    }
}
