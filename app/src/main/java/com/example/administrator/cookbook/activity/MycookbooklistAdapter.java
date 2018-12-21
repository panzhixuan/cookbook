package com.example.administrator.cookbook.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.MycookbooklistController;
import com.example.administrator.cookbook.model.cookbook;

import java.util.List;

public class MycookbooklistAdapter extends ArrayAdapter implements MycookbooklistControllerListener {
    private final int resourceId;
    private Context context;
    private View view;

    public MycookbooklistAdapter(Context context, int textViewResourceId,List<cookbook> objects) {
        super(context, textViewResourceId,objects);
        this.context=context;
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        cookbook cookbook = (cookbook) getItem(position);
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        MycookbooklistController Controller = new MycookbooklistController(view.findViewById(R.id.mycookbooklist), this);
        return view;
    }
}
