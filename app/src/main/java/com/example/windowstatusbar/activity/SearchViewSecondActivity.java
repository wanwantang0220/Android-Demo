package com.example.windowstatusbar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.adapter.CommonAdapter;

import java.util.ArrayList;

public class SearchViewSecondActivity extends AppCompatActivity {

    ImageView ivSearch;
    EditText etSearch;
    ImageView ivBack;

    RecyclerView rvBottom;
    RecyclerView rvTop;
    ArrayList<String> topList = new ArrayList<>();
    ArrayList<String> bottomList = new ArrayList<>();
    private CommonAdapter topAdapter;
    private CommonAdapter bottomAdapter;
    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_second);

        ivSearch = findViewById(R.id.iv_second_search);
        etSearch = findViewById(R.id.et_search);
        ivBack = findViewById(R.id.iv_second_back);
        rvBottom = findViewById(R.id.rv_bottom);
        rvTop = findViewById(R.id.rv_top);

        rvTop.setLayoutManager(new LinearLayoutManager(this));
        rvBottom.setLayoutManager(new LinearLayoutManager(this));
        showSoftInputFromWindow(this);
        onClickListener();

    }


    private void getBottomList() {
        for (int m = 0; m < 10; m++) {
            bottomList.add("搜索结果  第 " + m + "条数据");
        }
    }

    private void getTopList(String s) {
        topList.clear();
        for (int i = 0; i < 10; i++) {
            topList.add(s + " --  测试  第 " + i + "条数据");
        }
    }


    private void onClickListener() {
        ivBack.setOnClickListener(v -> onBackPressed());
//        etSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                text = s.toString();
//                Log.i("TAG", "TEXT = " + text);
//                initTop();
//            }
//        });
        etSearch.setOnClickListener(v -> {
            text = etSearch.getText().toString();
            Log.i("TAG", "TEXT = " + text);
            initTop();
        });
    }

    private void showSoftInputFromWindow(SearchViewSecondActivity activity) {
        etSearch.setFocusable(true);
        etSearch.setFocusableInTouchMode(true);
        etSearch.requestFocus();

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        initTop();

    }

    private void initTop() {
        rvTop.setVisibility(View.VISIBLE);
        getTopList(text);
        topAdapter = new CommonAdapter(this, topList);
        rvTop.setAdapter(topAdapter);
        topAdapter.setOnItemClickListener((view, position) -> {
            initBottom();
            etSearch.setText("");
        });
    }

    private void initBottom() {
        rvTop.setVisibility(View.GONE);
        topList.clear();
        topList = new ArrayList<>();
        getBottomList();
        bottomAdapter = new CommonAdapter(this, bottomList);
        rvBottom.setAdapter(bottomAdapter);
        bottomAdapter.setOnItemClickListener((view, position) -> Toast.makeText(SearchViewSecondActivity.this, "您选中了 第 " + position + "个", Toast.LENGTH_SHORT).show());
    }
}
