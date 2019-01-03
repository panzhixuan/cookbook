package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.model.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class BrowserpageActivity extends AppCompatActivity {
    private List<Cookbook> cookbookList = new ArrayList<Cookbook>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browsepage);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    private void loadData() {
        final User user= BmobUser.getCurrentUser(User.class);
        BmobQuery<Cookbook> query = new BmobQuery<Cookbook>();
        query.addWhereRelatedTo("browse", new BmobPointer(user));
        query.include("user");
        query.findObjects(new FindListener<Cookbook>() {
            @Override
            public void done(List<Cookbook> object,BmobException e) {
                if(e==null){
                    int n = object.size();
                    cookbookList.clear();
                    for (int i = 0; i < n; i++) {
                        Cookbook cookbook = object.get(i);
                        cookbookList.add(cookbook);
                    }
                    TypelistAdapter  adapter = new TypelistAdapter (BrowserpageActivity.this, R.layout.typelist,cookbookList);
                    ListView listView = (ListView) findViewById(R.id.listview);
                    listView.setAdapter(adapter);
                }else{
                    Log.i("bmob","失败："+e.getMessage());
                }
            }
        });
    }
}
