package com.example.windowstatusbar.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.adapter.ShareElementListAdapter;

import java.util.ArrayList;

public class ShareElementActivity extends Activity {

    private ArrayList<Integer> mListData = new ArrayList<Integer>();

    private ShareElementListAdapter mAdapter;

    ImageView img5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element);

        init();
        initClickListener();

    }


    private void init() {
        initListData();

        img5 = findViewById(R.id.img5);
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ShareElementActivity.this,img5,"shareImg").toBundle();
                startActivity(new Intent(ShareElementActivity.this, ShareElementTwoActivity.class),bundle);

            }
        });
    }

    private void initListData() {
        mListData = new ArrayList();
        mListData.add(R.mipmap.img1);
        mListData.add(R.mipmap.img2);
        mListData.add(R.mipmap.img3);
        mListData.add(R.mipmap.img4);
    }

    private void initClickListener() {
    }

}
