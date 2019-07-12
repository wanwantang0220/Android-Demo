package com.example.windowstatusbar.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.adapter.SingleTypeAdapter;
import com.example.windowstatusbar.callback.AppBarStateChangeListener;
import com.example.windowstatusbar.tool.SystemBarHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapsingToolbarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbar_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("H07000223");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());

        //method 1
        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setHeightAndPadding(this, mToolbar);


        ArrayList<String> mNames = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mNames.add("状态栏沉浸");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SingleTypeAdapter<String>(mNames, R.layout.item_tool_bar) {

            @SuppressLint("SetTextI18n")
            @Override
            public void bindView(ViewHolder holder, int position, View itemView) {
                TextView mItem = ButterKnife.findById(itemView, R.id.item);
                mItem.setText(getDataList().get(holder.getAdapterPosition()) + "-" + holder.getAdapterPosition());
            }
        });


        appbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    tvTitle.setVisibility(View.GONE);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText("hello");
                } else {
                    //中间状态
                    tvTitle.setVisibility(View.GONE);
                }
            }
        });
    }
}
