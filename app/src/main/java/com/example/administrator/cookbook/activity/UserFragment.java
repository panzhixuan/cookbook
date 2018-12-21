package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.cookbook.R;

public class UserFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.aa, null);
        return view;
    }

    public static UserFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

