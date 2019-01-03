package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.model.Cookbook;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SearchpageActivity extends AppCompatActivity {
    private List<Cookbook> cookbookList = new ArrayList<Cookbook>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpage);
        String name=getIntent().getStringExtra("name");
        loadData(name);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String name=getIntent().getStringExtra("name");
        loadData(name);
    }
    private void loadData(String name) {
        BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
        // bmobQuery.addWhereContains("ckb_name",name);
        bmobQuery.addWhereEqualTo("cb_name",name);
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
                    TypelistAdapter adapter = new TypelistAdapter(SearchpageActivity.this, R.layout.typelist,cookbookList);
                    ListView listView = (ListView) findViewById(R.id.listview);
                    listView.setAdapter(adapter);
                }
                else {
                    System.out.println(e.getErrorCode());
                }
            }
        });
    }
}
