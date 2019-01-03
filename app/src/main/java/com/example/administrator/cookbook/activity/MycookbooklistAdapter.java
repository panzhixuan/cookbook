package com.example.administrator.cookbook.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.EditcookbookpageController;
import com.example.administrator.cookbook.controller.MycookbooklistController;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.view.GetImageByUrl;

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
       final Cookbook cookbook = (Cookbook) getItem(position);
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView cover=(ImageView)view.findViewById(R.id.cover);
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView username=(TextView)view.findViewById(R.id.username);
        TextView browsenum=(TextView)view.findViewById(R.id.totalbrowsenum);
        TextView collectnum=(TextView)view.findViewById(R.id.totalcolnum);
        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(cover,cookbook.getCb_image().getFileUrl());
        name.setText(cookbook.getCb_name().toString());
        browsenum.setText(cookbook.getCb_browse().toString());
        collectnum.setText(cookbook.getCb_favorite().toString());
        username.setText(cookbook.getUser().getUser_nickname());
//        MycookbooklistController Controller = new MycookbooklistController(view.findViewById(R.id.mycookbooklist), this);
//        setListeners(Controller);
        FrameLayout frameLayout=(FrameLayout) view.findViewById(R.id.mycookbooklist);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,CookbookdetailpageActivity.class);
                Log.i("BOMB", cookbook.getObjectId());
                intent.putExtra("cookbook_id",cookbook.getObjectId());
                context.startActivity(intent);
            }
        });
        ImageView eidt=(ImageView)view.findViewById(R.id.edit);
        eidt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,EditcookbookpageActivity.class);
                Log.i("BOMB", cookbook.getObjectId());
                intent.putExtra("cookbook_id",cookbook.getObjectId());
                context.startActivity(intent);
            }
        });
        return view;
    }
    public void setListeners(View.OnClickListener onClickListener){
//        view.findViewById(R.id.mycookbooklist).setOnClickListener(onClickListener);
//        view.findViewById(R.id.edit).setOnClickListener(onClickListener);
    }
    @Override
    public void toEditcookbook(){
        Intent intent=null;
        intent=new Intent(context,EditcookbookpageActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void tocookbookdetail(){
//        Intent intent=null;
//        intent=new Intent(context,CookbookdetailpageActivity.class);
//        context.startActivity(intent);
    }
}
