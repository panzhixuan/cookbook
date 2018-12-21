package com.example.administrator.cookbook.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.TypelistController;
import com.example.administrator.cookbook.model.cookbook;

import java.util.List;

public class TypelistAdapter extends ArrayAdapter implements TypelistControllerListener{
    private final int resourceId;
    private Context context;
    private View view;

    public TypelistAdapter(Context context, int textViewResourceId, List<cookbook> objects) {
        super(context, textViewResourceId,objects);
        this.context=context;
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        cookbook cookbook = (cookbook) getItem(position);
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TypelistController Controller = new TypelistController(view.findViewById(R.id.typelist), this);
        setListeners(Controller);
        return view;
    }
    public void setListeners(View.OnClickListener onClickListener){
        view.findViewById(R.id.typelist).setOnClickListener(onClickListener);
    }
    @Override
    public void tocookbookdetail() {
        Intent intent=null;
        intent=new Intent(context,CookbookdetailpageActivity.class);
        context.startActivity(intent);
    }
}
