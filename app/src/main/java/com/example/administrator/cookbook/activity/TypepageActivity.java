package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.model.Cuisine;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class TypepageActivity extends AppCompatActivity{
    private List<Cookbook> cookbookList = new ArrayList<Cookbook>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typepage);
        String typename=getIntent().getStringExtra("typename");
        TextView type=(TextView)findViewById(R.id.type);
        type.setText(typename);
        loadData(typename);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String typename=getIntent().getStringExtra("typename");
        loadData(typename);
    }
    private void loadData(String name) {
        BmobQuery<Cuisine> CuisineQuery = new BmobQuery<>();
        CuisineQuery.addWhereEqualTo("name", name);
        CuisineQuery.findObjects(new FindListener<Cuisine>() {
            @Override
            public void done(List<Cuisine> object, BmobException e) {
                if (e == null) {
                    BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
                    // bmobQuery.addWhereContains("ckb_name",name);
                    bmobQuery.addWhereEqualTo("cuisine",object.get(0));
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
                                TypelistAdapter adapter = new TypelistAdapter(TypepageActivity.this, R.layout.typelist,cookbookList);
                                ListView listView = (ListView) findViewById(R.id.listview);
                                listView.setAdapter(adapter);
                            }
                            else {
                                System.out.println(e.getErrorCode());
                            }
                        }
                    });
                } else {
                }
            }
        });
    }
}