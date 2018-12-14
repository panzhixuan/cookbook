package com.example.administrator.cookbook.activity;

import android.content.Context;
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
import android.widget.ImageView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.MainpageController;
import com.example.administrator.cookbook.controller.RegisterpageController;
import com.example.administrator.cookbook.view.MainpageView;
import com.example.administrator.cookbook.view.RegisterpageView;

import java.util.ArrayList;
import java.util.List;

public class MainpageFragment extends Fragment implements MainpageControllerListener{
    private MainpageActivity mainpageActivity;
    private boolean actIsAlive=true;
    private List<ImageView> viewPagerData;
    private PagerAdapter viewPagerAdapter;
    private ViewPager viewpager;
    private Handler handler;
    private int currentPosition;

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
        View view = inflater.inflate(R.layout.mainpage, null);
        MainpageController Controller = new MainpageController((MainpageView) view.findViewById(R.id.mainpage), this);
        ((MainpageView) view.findViewById(R.id.mainpage)).setListeners(Controller);
        viewpager=(ViewPager)view.findViewById(R.id.viewpager);
        initData();
        initViewpager();
        initHandler();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        actIsAlive=true;
        autoViewPager();
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
                        sleep(3000);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    @Override
    public void test() {
        mainpageActivity.finish();
    }
}

