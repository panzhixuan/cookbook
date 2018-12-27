package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.MycookbookController;
import com.example.administrator.cookbook.model.Cookbook;

import java.util.ArrayList;
import java.util.List;

public class MycookbookActivity  extends AppCompatActivity implements MycookbookControllerListener{
    private List<Cookbook> cookbookList = new ArrayList<Cookbook>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycookbookpage);
        Cookbook cookbook=new Cookbook();
        cookbookList.add(cookbook);
        MycookbooklistAdapter adapter = new MycookbooklistAdapter(MycookbookActivity.this, R.layout.mycookbooklist,cookbookList);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        MycookbookController Controller = new MycookbookController(this.findViewById(R.id.mycookbookpage), this);
        setListeners(Controller);
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.create).setOnClickListener(onClickListener);
    }
    @Override
    public void toCreatecookbook(){
        Intent intent=null;
        intent=new Intent(MycookbookActivity.this,CreatecookbookpageActivity.class);
        startActivity(intent);
    }
}
