package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.MycookbookController;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.model.Cuisine;
import com.example.administrator.cookbook.model.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MycookbookActivity  extends AppCompatActivity implements MycookbookControllerListener{
    private List<Cookbook> cookbookList = new ArrayList<Cookbook>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycookbookpage);
        Cookbook cookbook=new Cookbook();
        cookbookList.add(cookbook);
//        MycookbooklistAdapter adapter = new MycookbooklistAdapter(MycookbookActivity.this, R.layout.mycookbooklist,cookbookList);
//        ListView listView = (ListView) findViewById(R.id.listview);
//        listView.setAdapter(adapter);
        MycookbookController Controller = new MycookbookController(this.findViewById(R.id.mycookbookpage), this);
        setListeners(Controller);
        loadData();
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        User user= BmobUser.getCurrentUser(User.class);
        BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
//        bmobQuery.addWhereContains("user",user);
        bmobQuery.addWhereEqualTo("user",user);
        bmobQuery.include("user");
        bmobQuery.findObjects(new FindListener<Cookbook>() {
            @Override
            public void done(List<Cookbook> list, BmobException e) {
                if (e == null) {
                    int n = list.size();
                    cookbookList.clear();
                    System.out.println(n);
                    for (int i = 0; i < n; i++) {
                        Cookbook cookbook = list.get(i);
                        cookbookList.add(cookbook);
                    }
                    MycookbooklistAdapter adapter = new MycookbooklistAdapter(MycookbookActivity.this, R.layout.mycookbooklist,cookbookList);
                    ListView listView = (ListView) findViewById(R.id.listview);
                    listView.setAdapter(adapter);
                }
                else {
                    System.out.println(e.getErrorCode());
                }
            }
        });
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
