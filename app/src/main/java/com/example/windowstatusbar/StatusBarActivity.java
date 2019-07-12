package com.example.windowstatusbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;

public class StatusBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);


//        StatusBarUtil.setTransparent(this);
        StatusBarUtil.setTranslucent(this , 5);

//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
    }
}
