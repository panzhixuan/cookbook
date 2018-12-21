package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.model.cookbook;

import java.util.ArrayList;
import java.util.List;

public class MycookbookActivity  extends AppCompatActivity {
    private List<cookbook> cookbookList = new ArrayList<cookbook>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycookbookpage);
        cookbook cookbook=new cookbook();
        cookbookList.add(cookbook);
        MycookbooklistAdapter adapter = new MycookbooklistAdapter(MycookbookActivity.this, R.layout.mycookbooklist,cookbookList);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}