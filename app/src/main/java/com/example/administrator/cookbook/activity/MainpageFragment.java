package com.example.administrator.cookbook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.MainpageController;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.view.GetImageByUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainpageFragment extends Fragment implements MainpageControllerListener{
    private MainpageActivity mainpageActivity;
    private boolean actIsAlive=true;
    private List<ImageView> viewPagerData;
    private PagerAdapter viewPagerAdapter;
    private ViewPager viewpager;
    private Handler handler;
    private int currentPosition;
    private View view;
    private List<Cookbook> cookbookList = new ArrayList<Cookbook>();
    private List<String> mStrings = new ArrayList<String>();

    //onAttach(),当fragment被绑定到activity时被调用(Activity会被传入.).
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
        view = inflater.inflate(R.layout.mainpage, null);
        MainpageController Controller = new MainpageController(view.findViewById(R.id.mainpage), this);
        setListeners(Controller);
        viewpager=(ViewPager)view.findViewById(R.id.viewpager);
        initData();
        initViewpager();
        initHandler();
        loadData();
        loadmainData();
        final AutoCompleteTextView search=(AutoCompleteTextView)view.findViewById(R.id.search);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mainpageActivity,android.R.layout.simple_dropdown_item_1line,mStrings);
        search.setAdapter(adapter);
        ImageView sync=(ImageView)view.findViewById(R.id.sync);
        return view;
    }
    public void setListeners(View.OnClickListener onClickListener){
        view.findViewById(R.id.type1).setOnClickListener(onClickListener);
        view.findViewById(R.id.type2).setOnClickListener(onClickListener);
        view.findViewById(R.id.type3).setOnClickListener(onClickListener);
        view.findViewById(R.id.type4).setOnClickListener(onClickListener);
        view.findViewById(R.id.type5).setOnClickListener(onClickListener);
        view.findViewById(R.id.type6).setOnClickListener(onClickListener);
        view.findViewById(R.id.type7).setOnClickListener(onClickListener);
        view.findViewById(R.id.type8).setOnClickListener(onClickListener);
        view.findViewById(R.id.searchconfirm).setOnClickListener(onClickListener);
        view.findViewById(R.id.sync).setOnClickListener(onClickListener);
//        view.findViewById(R.id.recdish1).setOnClickListener(onClickListener);
//        view.findViewById(R.id.recdish2).setOnClickListener(onClickListener);
//        view.findViewById(R.id.recdish3).setOnClickListener(onClickListener);
//        view.findViewById(R.id.recdish4).setOnClickListener(onClickListener);
    }
    @Override
    public void onResume() {
        super.onResume();
        actIsAlive=true;
        autoViewPager();
        loadmainData();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        actIsAlive=false;
    }

    public static MainpageFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        MainpageFragment fragment = new MainpageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void initData() {
        viewPagerData = new ArrayList<>();
        ImageView imageView = new ImageView(mainpageActivity);
        imageView.setBackgroundResource(R.drawable.chuancai);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        viewPagerData.add(imageView);

        ImageView imageView2 = new ImageView(mainpageActivity);
        imageView2.setBackgroundResource(R.drawable.huaicai);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        viewPagerData.add(imageView2);

        ImageView imageView3 = new ImageView(mainpageActivity);
        imageView3.setBackgroundResource(R.drawable.huicai);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        viewPagerData.add(imageView3);

        ImageView imageView4 = new ImageView(mainpageActivity);
        imageView4.setBackgroundResource(R.drawable.lucai);
        imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
        viewPagerData.add(imageView4);

        ImageView imageView5 = new ImageView(mainpageActivity);
        imageView5.setBackgroundResource(R.drawable.mincai);
        imageView5.setScaleType(ImageView.ScaleType.FIT_XY);
        viewPagerData.add(imageView5);

        ImageView imageView6 = new ImageView(mainpageActivity);
        imageView6.setBackgroundResource(R.drawable.yuecai);
        imageView6.setScaleType(ImageView.ScaleType.FIT_XY);
        viewPagerData.add(imageView6);

        ImageView imageView7 = new ImageView(mainpageActivity);
        imageView7.setBackgroundResource(R.drawable.xiangcai);
        imageView7.setScaleType(ImageView.ScaleType.FIT_XY);
        viewPagerData.add(imageView7);

        ImageView imageView8 = new ImageView(mainpageActivity);
        imageView8.setBackgroundResource(R.drawable.zhecai);
        imageView8.setScaleType(ImageView.ScaleType.FIT_XY);
        viewPagerData.add(imageView8);
    }
    private void initViewpager() {
        //数据适配器
        viewPagerAdapter = new PagerAdapter() {
            private int mChildCount = 0;

            @Override
            public void notifyDataSetChanged() {
                mChildCount = getCount();
                super.notifyDataSetChanged();
            }

            @Override
            public int getItemPosition(Object object) {
                if (mChildCount > 0) {
                    mChildCount--;
                    return POSITION_NONE;
                }
                return super.getItemPosition(object);
            }

            @Override
            //获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return viewPagerData.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            //是从ViewGroup中移出当前View
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(viewPagerData.get(arg1));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            public Object instantiateItem(View arg0, int arg1) {
                ((ViewPager) arg0).addView(viewPagerData.get(arg1));
                return viewPagerData.get(arg1);
            }
        };

        viewpager.setAdapter(viewPagerAdapter);
        viewpager.setCurrentItem(0);
        viewpager.setOffscreenPageLimit(7);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {
                // 没有滑动的时候 切换页面
            }
        });
    }
    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    if (currentPosition==viewPagerData.size()-1){          // 如果当前位置是轮播图的最后一个位置，则调到轮播图数据源的第一张图片
                        currentPosition = 0 ;
                        viewpager.setCurrentItem(0,false);
                    }else{
                        currentPosition ++;                                // 否则切换到下一张图片
                        viewpager.setCurrentItem(currentPosition,true);
                    }
                }
            }
        };
    }
    private void autoViewPager() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (actIsAlive) {
                    try {
                        sleep(5000);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    @Override
    public void toTypeActivity(String typename) {
        Intent intent=null;
        intent=new Intent(mainpageActivity,TypepageActivity.class);
        intent.putExtra("typename",typename);
        startActivity(intent);
    }
    @Override
    public void toSearchActivity() {
        EditText search=(EditText)view.findViewById(R.id.search);
        if(search.getText().toString().equals("")) {
            Toast.makeText(mainpageActivity, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = null;
            intent = new Intent(mainpageActivity, SearchpageActivity.class);
            intent.putExtra("name",search.getText().toString());
            startActivity(intent);
        }
    }
    @Override
    public void toCookbookdetail(){
//        Intent intent=null;
//        intent=new Intent(mainpageActivity,CookbookdetailpageActivity.class);
//        startActivity(intent);
    }

    @Override
    public void sync(){
        loadmainData();
    }

    private void loadData() {
        BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
        bmobQuery.include("user");
        bmobQuery.findObjects(new FindListener<Cookbook>() {
            @Override
            public void done(List<Cookbook> list, BmobException e) {
                if (e == null) {
                    int n = list.size();
                    System.out.println(n);
                    mStrings.clear();
                    for (int i = 0; i < n; i++) {
                        Cookbook cookbook = list.get(i);
                        mStrings.add(cookbook.getCb_name());
                    }
                    System.out.println("mString"+mStrings.size());
                }
                else {
                    System.out.println(e.getErrorCode());
                }
            }
        });
    }
    private void loadmainData() {
        BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
        bmobQuery.include("user");
        bmobQuery.findObjects(new FindListener<Cookbook>() {
            @Override
            public void done(List<Cookbook> list, BmobException e) {
                if (e == null) {
                    int n = list.size();
                    System.out.println(n);
                    cookbookList.clear();
                    for (int i = 0; i < n; i++) {
                        Cookbook cookbook = list.get(i);
                        cookbookList.add(cookbook);
                    }
                    ImageView cover1=(ImageView)view.findViewById(R.id.recdish1cover);
                    TextView dishname1=(TextView)view.findViewById(R.id.recdish1name);
                    TextView username1=(TextView)view.findViewById(R.id.recdish1username);
                    ImageView cover2=(ImageView)view.findViewById(R.id.recdish2cover);
                    TextView dishname2=(TextView)view.findViewById(R.id.recdish2name);
                    TextView username2=(TextView)view.findViewById(R.id.recdish2username);
                    ImageView cover3=(ImageView)view.findViewById(R.id.recdish3cover);
                    TextView dishname3=(TextView)view.findViewById(R.id.recdish3name);
                    TextView username3=(TextView)view.findViewById(R.id.recdish3username);
                    ImageView cover4=(ImageView)view.findViewById(R.id.recdish4cover);
                    TextView dishname4=(TextView)view.findViewById(R.id.recdish4name);
                    TextView username4=(TextView)view.findViewById(R.id.recdish4username);
                    Random random = new Random();

                    int a1= random.nextInt(n-1)%(n) + 0;
                    int a2= random.nextInt(n-1)%(n) + 0;
                    while(a2 == a1){
                        a2= random.nextInt(n-1)%(n) + 0;
                    }
                    int a3= random.nextInt(n-1)%(n) + 0;
                    while(a3==a1||a3==a2){
                        a3= random.nextInt(n-1)%(n) + 0;
                    }
                    int a4= random.nextInt(n-1)%(n) + 0;
                    while(a4==a1||a4==a2||a4==a3){
                        a4= random.nextInt(n-1)%(n) + 0;
                    }
                    GetImageByUrl getImageByUrl1 = new GetImageByUrl();
                    getImageByUrl1.setImage(cover1,cookbookList.get(a1).getCb_image().getFileUrl());
                    GetImageByUrl getImageByUrl2 = new GetImageByUrl();
                    getImageByUrl2.setImage(cover2,cookbookList.get(a2).getCb_image().getFileUrl());
                    GetImageByUrl getImageByUrl3 = new GetImageByUrl();
                    getImageByUrl3.setImage(cover3,cookbookList.get(a3).getCb_image().getFileUrl());
                    GetImageByUrl getImageByUrl4 = new GetImageByUrl();
                    getImageByUrl4.setImage(cover4,cookbookList.get(a4).getCb_image().getFileUrl());
                    dishname1.setText(cookbookList.get(a1).getCb_name());
                    dishname2.setText(cookbookList.get(a2).getCb_name());
                    dishname3.setText(cookbookList.get(a3).getCb_name());
                    dishname4.setText(cookbookList.get(a4).getCb_name());
                    username1.setText(cookbookList.get(a1).getUser().getUser_nickname());
                    username2.setText(cookbookList.get(a2).getUser().getUser_nickname());
                    username3.setText(cookbookList.get(a3).getUser().getUser_nickname());
                    username4.setText(cookbookList.get(a4).getUser().getUser_nickname());
                    final int b1=a1;
                    final int b2=a2;
                    final int b3=a3;
                    final int b4=a4;
                    view.findViewById(R.id.recdish1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(mainpageActivity,CookbookdetailpageActivity.class);
                            Log.i("BOMB", cookbookList.get(b1).getObjectId());
                            intent.putExtra("cookbook_id",cookbookList.get(b1).getObjectId());
                            startActivity(intent);
                        }
                    });
                    view.findViewById(R.id.recdish2).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(mainpageActivity,CookbookdetailpageActivity.class);
                            Log.i("BOMB", cookbookList.get(b2).getObjectId());
                            intent.putExtra("cookbook_id",cookbookList.get(b2).getObjectId());
                            startActivity(intent);
                        }
                    });
                    view.findViewById(R.id.recdish3).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(mainpageActivity,CookbookdetailpageActivity.class);
                            Log.i("BOMB", cookbookList.get(b3).getObjectId());
                            intent.putExtra("cookbook_id",cookbookList.get(b3).getObjectId());
                            startActivity(intent);
                        }
                    });
                    view.findViewById(R.id.recdish4).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(mainpageActivity,CookbookdetailpageActivity.class);
                            Log.i("BOMB", cookbookList.get(b4).getObjectId());
                            intent.putExtra("cookbook_id",cookbookList.get(b4).getObjectId());
                            startActivity(intent);
                        }
                    });
                }
                else {
                    System.out.println(e.getErrorCode());
                }
            }
        });
    }
}

