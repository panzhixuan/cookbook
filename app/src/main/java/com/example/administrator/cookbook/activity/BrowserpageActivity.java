package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.model.cookbook;

import java.util.ArrayList;
import java.util.List;

public class BrowserpageActivity extends AppCompatActivity {
    private List<cookbook> cookbookList = new ArrayList<cookbook>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browsepage);
        cookbook cookbook=new cookbook();
        cookbookList.add(cookbook);
        TypelistAdapter adapter = new TypelistAdapter(BrowserpageActivity.this, R.layout.browselist,cookbookList);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
