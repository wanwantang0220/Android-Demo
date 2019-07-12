package com.example.windowstatusbar.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.windowstatusbar.R;

import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> view;
    private List<String> titles;
    private LayoutInflater mInflater;
    private Context mContext;


    public MainPagerAdapter(Context context ,FragmentManager fragmentManager, List<Fragment> views, List<String> titles) {
        super(fragmentManager);
        this.mContext = context;
        this.titles = titles;
        this.view = views;
    }

    public MainPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        return view.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return view.size();
    }

    //没有自定义view的话，这里是显示tab标题的名称，但是自定义之后就需要把这里消除掉
    // @Override
    // public CharSequence getPageTitle(int position) {
    //
    // return titles.get(position);
    // }
//因为是两个不同的自定义Tab我就分成两个写了，不知道大家有什么更好的处理方法。
    public View getTabView0() {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.item_tab_left, null);
        TextView tv = (TextView) view.findViewById(R.id.tab_text_left);
        tv.setText(titles.get(0));
        return view;
    }

    public View getTabView1() {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.item_tab_right, null);
        TextView tv = (TextView) view.findViewById(R.id.tab_text_right);
        tv.setText(titles.get(1));
        return view;
    }

}
