package com.example.administrator.cookbook.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.EditcookbookpageController;
import com.example.administrator.cookbook.controller.MycookbooklistController;
import com.example.administrator.cookbook.model.Cookbook;

import java.util.List;

public class MycookbooklistAdapter extends ArrayAdapter implements MycookbooklistControllerListener {
    private final int resourceId;
    private Context context;
    private View view;

    public MycookbooklistAdapter(Context context, int textViewResourceId,List<Cookbook> objects) {
        super(context, textViewResourceId,objects);
        this.context=context;
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        Cookbook cookbook = (Cookbook) getItem(position);
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        MycookbooklistController Controller = new MycookbooklistController(view.findViewById(R.id.mycookbooklist), this);
        setListeners(Controller);
        return view;
    }
    public void setListeners(View.OnClickListener onClickListener){
        view.findViewById(R.id.mycookbooklist).setOnClickListener(onClickListener);
        view.findViewById(R.id.edit).setOnClickListener(onClickListener);
    }
    @Override
    public void toEditcookbook(){
        Intent intent=null;
        intent=new Intent(context,EditcookbookpageActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void tocookbookdetail(){
        Intent intent=null;
        intent=new Intent(context,CookbookdetailpageActivity.class);
        context.startActivity(intent);
    }
}
