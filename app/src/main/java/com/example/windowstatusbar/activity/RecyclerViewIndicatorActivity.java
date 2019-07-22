package com.example.windowstatusbar.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.RecyclemAdapter;
import com.example.windowstatusbar.adapter.RecyclemIndicatorAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class RecyclerViewIndicatorActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FrameLayout flIndicatorContainer;
    FrameLayout flIndicator;
    private ArrayList<Integer> mList = new ArrayList<>();

    private RecyclemIndicatorAdapter adapter;
    private float endX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_indicator);
        recyclerView = findViewById(R.id.recycler_view);
        flIndicatorContainer = findViewById(R.id.flIndicatorContainer);
        flIndicator = findViewById(R.id.flIndicator);


        getList();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclemIndicatorAdapter(this, mList);
        recyclerView.setAdapter(adapter);

        if (mList.size() > 5) {
            flIndicatorContainer.setVisibility(View.VISIBLE);
        } else {
            flIndicatorContainer.setVisibility(View.GONE);
        }


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //RecyclerView整体宽度，包括显示区域之外
                int range = recyclerView.computeHorizontalScrollRange();
                //RecyclerView显示区域宽度
                int extent = recyclerView.computeHorizontalScrollExtent();
                //可滑动距离
                float distance = range - extent;
                Log.i("TAG", "range = " + range);
                Log.i("TAG", "extent = " + extent);
                Log.i("TAG", "distance = " + distance);

                //可滑动距离大于0才能滑动
                if (distance > 0) {
                    //滑动的距离
                    endX += dx;
                    //计算滑动比例
                    float scale = endX / distance;
                    //计算滚动条可滚动宽度
                    int indicatorWidth = flIndicatorContainer.getWidth() - flIndicator.getWidth();
                    //滚动的距离
                    float scrollDistance = indicatorWidth * scale;

                    Log.i("TAG", "scale = " + scale);
                    Log.i("TAG", "indicatorWidth = " + indicatorWidth);
                    Log.i("TAG", "scrollDistance = " + scrollDistance);

                    //设置滚动条滚动
                    flIndicator.setTranslationX(scrollDistance);
                }
            }
        });
    }

    private void getList() {
        mList.add(R.mipmap.f1);
        mList.add(R.mipmap.f2);
        mList.add(R.mipmap.f3);
        mList.add(R.mipmap.f4);
        mList.add(R.mipmap.f5);
        mList.add(R.mipmap.z8);
        mList.add(R.mipmap.z7);
        mList.add(R.mipmap.z6);
        mList.add(R.mipmap.pic1);
        mList.add(R.mipmap.pic2);
        mList.add(R.mipmap.pic3);
    }
}
