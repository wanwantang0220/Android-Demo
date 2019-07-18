package com.example.windowstatusbar.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.windowstatusbar.R;

public class SearchViewSecondActivity extends AppCompatActivity {

    ImageView ivSearch;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_second);

        ivSearch = findViewById(R.id.iv_second_search);
        etSearch = findViewById(R.id.et_search);


        showSoftInputFromWindow(this);
    }

    private void showSoftInputFromWindow(SearchViewSecondActivity activity) {
        etSearch.setFocusable(true);
        etSearch.setFocusableInTouchMode(true);
        etSearch.requestFocus();

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }
}
