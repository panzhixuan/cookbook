package com.example.administrator.cookbook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.TypePageController;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends Fragment implements TypePageControllerListener{
    private MainpageActivity mainpageActivity;
    private List<Button> buttonList;
    private List<Button> buttonList1;
    private List<Button> buttonList2;

    private View view;

    private TypePageController controller;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainpageActivity = (MainpageActivity) context;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get("name").toString();
            Log.d("MyFragment", name);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.maintypepage, null);

        initView();

        controller = new TypePageController(view.findViewById(R.id.maintypepage), this);
        setListeners(controller);


        return view;
    }

    public static TypeFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        TypeFragment fragment = new TypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void resetView() {
        for (int i=0; i<buttonList.size(); i++) {
            buttonList.get(i).setBackgroundResource(R.drawable.caishi_shape);
        }
        for (int i=0; i<buttonList.size(); i++) {
            buttonList1.get(i).setBackgroundResource(R.drawable.caishi_shape);
        }
        for (int i=0; i<buttonList.size(); i++) {
            buttonList2.get(i).setBackgroundResource(R.drawable.caishi_shape);
        }
    }
    public void setListeners(View.OnClickListener onClickListener){
        view.findViewById(R.id.confirm).setOnClickListener(onClickListener);
    }

    @Override
    public void changeView(int id) {

    }


    public void initView() {
        buttonList = new ArrayList<>();
        buttonList1 = new ArrayList<>();
        buttonList2 = new ArrayList<>();

        Button button00 = view.findViewById(R.id.button00);
        button00.setOnClickListener(controller);
        buttonList.add(button00);

        Button button10 = view.findViewById(R.id.button10);
        button10.setOnClickListener(controller);
        buttonList.add(button10);

        Button button20 = view.findViewById(R.id.button20);
        button20.setOnClickListener(controller);
        buttonList.add(button20);


        Button button01 = view.findViewById(R.id.button01);
        button01.setOnClickListener(controller);
        buttonList1.add(button01);

        Button button11 = view.findViewById(R.id.button11);
        button11.setOnClickListener(controller);
        buttonList1.add(button11);

        Button button21 = view.findViewById(R.id.button21);
        button21.setOnClickListener(controller);
        buttonList1.add(button21);


        Button button02 = view.findViewById(R.id.button02);
        button02.setOnClickListener(controller);
        buttonList2.add(button02);

        Button button12 = view.findViewById(R.id.button12);
        button12.setOnClickListener(controller);
        buttonList2.add(button12);

        Button button22 = view.findViewById(R.id.button22);
        button22.setOnClickListener(controller);
        buttonList2.add(button22);

    }
    @Override
    public void submit() {
        Intent intent=null;
        intent=new Intent(mainpageActivity,TypesearchpageActivity.class);
        startActivity(intent);
    }
}

