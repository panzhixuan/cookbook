package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.model.cookbook;

import java.util.ArrayList;
import java.util.List;

public class TypepageActivity extends AppCompatActivity{
    private List<cookbook> cookbookList = new ArrayList<cookbook>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typepage);
        String typename=getIntent().getStringExtra("typename");
        TextView type=(TextView)findViewById(R.id.type);
        type.setText(typename);
        cookbook cookbook=new cookbook();
        cookbookList.add(cookbook);
        TypelistAdapter adapter = new TypelistAdapter(TypepageActivity.this, R.layout.typelist,cookbookList);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}