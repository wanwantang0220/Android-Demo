package com.example.windowstatusbar.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.windowstatusbar.R;

public class SearchViewActivity extends AppCompatActivity {

    ImageView ivSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);


        ivSearch = findViewById(R.id.iv_search);


        ivSearch.setOnClickListener(v -> {
            Intent intent = new Intent(SearchViewActivity.this, SearchViewSecondActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(SearchViewActivity.this, ivSearch, "searchShareImg").toBundle();
            startActivity(intent,bundle);
        });
    }
}
