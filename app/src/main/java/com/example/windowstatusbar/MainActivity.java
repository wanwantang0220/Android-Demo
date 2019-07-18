package com.example.windowstatusbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;

import com.example.windowstatusbar.callback.AppBarStateChangeListener;
import com.example.windowstatusbar.callback.GlideImageLoader;
import com.example.windowstatusbar.util.AppConstant;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Banner banner;

    Toolbar toolbar;
    RecyclerView recyclerView;

    AppBarLayout mAppBarLayout;//标题部分
    CollapsingToolbarLayout mCollapsingToolbarLayout;//折叠式标题栏


    RecyclemAdapter mAdapter;
    ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_red_light));

//        initTool();


        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        mAppBarLayout = findViewById(R.id.appbar_layout);
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        banner = findViewById(R.id.banner);
        mCollapsingToolbarLayout.setTitle("Hello");

        initRecyle();
        initBanner();
    }

    private void initTool() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        mAppBarLayout = findViewById(R.id.appbar_layout);
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        banner = findViewById(R.id.banner);
        mCollapsingToolbarLayout.setTitle("Hello");

        //设置状态栏
        //沉浸式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#11B7F3"));//设置收缩后Toolbar上字体的颜色
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //给页面设置工具栏
        if (mCollapsingToolbarLayout != null) {
            //设置隐藏图片时候ToolBar的颜色
            mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
            //设置工具栏标题
            mCollapsingToolbarLayout.setTitle("编程是一种信仰");
        }


        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //展开状态
                } else if (state == State.COLLAPSED) {
                    mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
                    //折叠状态
                } else {

                    //中间状态
//                    Toast.makeText(getActivity(),"中间状态",Toast.LENGTH_SHORT).show();

                }
            }
        });

        initRecyle();
        initBanner();
    }

    private void initRecyle() {
        mList.add(AppConstant.LIST_TITLE25);
        mList.add(AppConstant.LIST_TITLE24);
        mList.add(AppConstant.LIST_TITLE23);
        mList.add(AppConstant.LIST_TITLE22);
        mList.add(AppConstant.LIST_TITLE21);
        mList.add(AppConstant.LIST_TITLE20);
        mList.add(AppConstant.LIST_TITLE1);
        mList.add(AppConstant.LIST_TITLE2);
        mList.add(AppConstant.LIST_TITLE3);
        mList.add(AppConstant.LIST_TITLE4);
        mList.add(AppConstant.LIST_TITLE5);
        mList.add(AppConstant.LIST_TITLE6);
        mList.add(AppConstant.LIST_TITLE7);
        mList.add(AppConstant.LIST_TITLE8);
        mList.add(AppConstant.LIST_TITLE9);
        mList.add(AppConstant.LIST_TITLE10);
        mList.add(AppConstant.LIST_TITLE11);
        mList.add(AppConstant.LIST_TITLE12);
        mList.add(AppConstant.LIST_TITLE13);
        mList.add(AppConstant.LIST_TITLE14);
        mList.add(AppConstant.LIST_TITLE15);
//        mList.add(AppConstant.LIST_TITLE16);
        mList.add(AppConstant.LIST_TITLE17);
        mList.add(AppConstant.LIST_TITLE18);
        mList.add(AppConstant.LIST_TITLE19);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclemAdapter adpter = new RecyclemAdapter(this);
        adpter.setData(mList);
        recyclerView.setAdapter(adpter);
    }


    private void initBanner() {
        List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.h1);
        images.add(R.mipmap.h2);
        images.add(R.mipmap.h3);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
    }
}
