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
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.UserPageController;

import org.w3c.dom.Text;

public class UserFragment extends Fragment implements UserpageControllerListener{
    private MainpageActivity mainpageActivity;


    private EditText editTextUserName;
    private Button buttonCaishi;
    private Button buttonCaixi;
    private Button buttonChanghe;
    private EditText editTextRegion;

    private TextView textViewMyCollection;
    private TextView textViewMyView;
    private TextView textViewMyCaipu;
    private UserPageController controller;
    private View view;


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
        view = inflater.inflate(R.layout.userpage, null);

        editTextUserName = view.findViewById(R.id.username);
        buttonCaishi = view.findViewById(R.id.caishi);
        buttonCaixi = view.findViewById(R.id.caixi);
        buttonChanghe = view.findViewById(R.id.changhe);
        editTextRegion = view.findViewById(R.id.region);
        textViewMyCollection = view.findViewById(R.id.my_collection);
        textViewMyView = view.findViewById(R.id.my_view);
        textViewMyCaipu = view.findViewById(R.id.my_caipu);

        controller = new UserPageController(view.findViewById(R.id.userpage), this);
        setListeners(controller);
        return view;
    }

    public static UserFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void init() {

    }

    public void setListeners(View.OnClickListener onClickListener){
        view.findViewById(R.id.username).setOnClickListener(onClickListener);
        view.findViewById(R.id.caishi).setOnClickListener(onClickListener);
        view.findViewById(R.id.caixi).setOnClickListener(onClickListener);
        view.findViewById(R.id.changhe).setOnClickListener(onClickListener);
        view.findViewById(R.id.region).setOnClickListener(onClickListener);
        view.findViewById(R.id.my_collection).setOnClickListener(onClickListener);
        view.findViewById(R.id.my_view).setOnClickListener(onClickListener);
        view.findViewById(R.id.my_caipu).setOnClickListener(onClickListener);
        view.findViewById(R.id.logout).setOnClickListener(onClickListener);
    }
    @Override
    public void toCollectpage(){
        Intent intent=null;
        intent=new Intent(mainpageActivity,CollectpageActivity.class);
        startActivity(intent);
    }
    @Override
    public void toBrowserpage(){
        Intent intent=null;
        intent=new Intent(mainpageActivity,BrowserpageActivity.class);
        startActivity(intent);
    }
    @Override
    public void toMycookbook(){
        Intent intent=null;
        intent=new Intent(mainpageActivity,MycookbookActivity.class);
        startActivity(intent);
    }
    @Override
    public void logout() {
        Intent intent=null;
        intent=new Intent(mainpageActivity,LogginpageActivity.class);
        startActivity(intent);
    }
}

