package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.model.Cuisine;
import com.example.administrator.cookbook.model.Occasion;
import com.example.administrator.cookbook.model.Taste;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class TypesearchpageActivity extends AppCompatActivity{
    private List<Cookbook> cookbookList = new ArrayList<Cookbook>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpage);
        String caishi=getIntent().getStringExtra("caishi");
        String caixi=getIntent().getStringExtra("caixi");
        String changhe=getIntent().getStringExtra("changhe");
        Log.i("login1",caishi);
        Log.i("login1",caixi);
        Log.i("login1",changhe);
        loadData(caishi,caixi,changhe);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String caishi=getIntent().getStringExtra("caishi");
        String caixi=getIntent().getStringExtra("caixi");
        String changhe=getIntent().getStringExtra("changhe");
        loadData(caishi,caixi,changhe);
    }
    private void loadData(final String caishi, final String caixi, final String changhe) {
        BmobQuery<Cuisine> Query = new BmobQuery<>();
        Query.addWhereEqualTo("name", caixi);
        Query.findObjects(new FindListener<Cuisine>() {
            @Override
            public void done(final List<Cuisine> cuisines, BmobException e) {
                if (e == null) {
                    Log.i("login2",cuisines.get(0).getName());
                    BmobQuery<Taste> Query = new BmobQuery<>();
                    Query.addWhereEqualTo("name", caishi);
                    Query.findObjects(new FindListener<Taste>() {
                        @Override
                        public void done(final List<Taste> tastes, BmobException e) {
                            if (e == null) {
                                Log.i("login2",tastes.get(0).getName());
                                BmobQuery<Occasion> Query = new BmobQuery<>();
                                Query.addWhereEqualTo("name", changhe);
                                Query.findObjects(new FindListener<Occasion>() {
                                    @Override
                                    public void done(List<Occasion> changhes, BmobException e) {
                                        if (e == null) {
                                            Log.i("login2",changhes.get(0).getName());
                                            BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
                                            // bmobQuery.addWhereContains("ckb_name",name);
                                            bmobQuery.addWhereEqualTo("taste",tastes.get(0).getObjectId());
                                            bmobQuery.addWhereEqualTo("cuisine",cuisines.get(0).getObjectId());
                                            bmobQuery.addWhereEqualTo("occasion",changhes.get(0).getObjectId());
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
                                                        TypelistAdapter adapter = new TypelistAdapter(TypesearchpageActivity.this, R.layout.typelist,cookbookList);
                                                        ListView listView = (ListView) findViewById(R.id.listview);
                                                        listView.setAdapter(adapter);
                                                    }
                                                    else {
                                                        System.out.println(e.getErrorCode());
                                                    }
                                                }
                                            });
                                        } else {
                                            Log.i("BMOB", e.toString());
                                        }
                                    }
                                });
                            } else {
                                Log.i("BMOB", e.toString());
                            }
                        }
                    });
                } else {
                    Log.i("BMOB", e.toString());
                }
            }
        });
    }
}
