package com.example.administrator.cookbook.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.BrowserlistController;
import com.example.administrator.cookbook.model.Cookbook;

import java.util.List;

public class BrowserlistAdapter  extends ArrayAdapter implements BrowserlistControllerListener{
    private final int resourceId;
    private Context context;
    private View view;

    public BrowserlistAdapter(Context context, int textViewResourceId,List<Cookbook> objects) {
        super(context, textViewResourceId,objects);
        this.context=context;
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        Cookbook cookbook = (Cookbook) getItem(position);
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        BrowserlistController Controller = new BrowserlistController(view.findViewById(R.id.browselist), this);
        setListeners(Controller);
        return view;
    }
    public void setListeners(View.OnClickListener onClickListener){
        view.findViewById(R.id.browselist).setOnClickListener(onClickListener);
    }
    @Override
    public void tocookbookdetail() {
        Intent intent=null;
        intent=new Intent(context,CookbookdetailpageActivity.class);
        context.startActivity(intent);
    }
}
