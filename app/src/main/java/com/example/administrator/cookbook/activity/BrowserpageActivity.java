package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.BrowserlistController;
import com.example.administrator.cookbook.model.Cookbook;

import java.util.ArrayList;
import java.util.List;

public class BrowserpageActivity extends AppCompatActivity {
    private List<Cookbook> cookbookList = new ArrayList<Cookbook>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browsepage);
        Cookbook cookbook=new Cookbook();
        cookbookList.add(cookbook);
        BrowserlistAdapter adapter = new BrowserlistAdapter(BrowserpageActivity.this, R.layout.browselist,cookbookList);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
