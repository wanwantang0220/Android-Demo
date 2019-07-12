package com.example.windowstatusbar;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.windowstatusbar.fragment.AFragment;
import com.example.windowstatusbar.fragment.BFragment;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private List<Fragment> datas;// 数据源
    private List<String> titles;
    private ViewPager viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initDatas();
        initView();
    }

    private void initDatas() {
        datas = new ArrayList<Fragment>();
        titles = new ArrayList<String>();
        datas.add(new AFragment());
        datas.add(new BFragment());
        titles.add("电桩详情");
        titles.add("充电终端");
    }

    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPage = findViewById(R.id.viewPage);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab1) {
                viewPage.setCurrentItem(tab1.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab1) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab1) {
            }
        });

        MainPagerAdapter mAdapter = new MainPagerAdapter(this,getSupportFragmentManager(), datas,
                titles);
        tabLayout.setTabsFromPagerAdapter(mAdapter);
        TabLayout.TabLayoutOnPageChangeListener listener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        viewPage.addOnPageChangeListener(listener);
        viewPage.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPage);
        //这里就是将两个自定义tab添加到tablayout中
        TabLayout.Tab tabs = tabLayout.getTabAt(0);
        tabs.setCustomView(mAdapter.getTabView0());
        tabs = tabLayout.getTabAt(1);
        tabs.setCustomView(mAdapter.getTabView1());
        //这一行代码是为了实现从别的页面跳转过来之后可以跳转到不同的tab和viewpage下，而不是老是默认从第一个开始。
//        viewPage.setCurrentItem(currentTab);
    }
}
