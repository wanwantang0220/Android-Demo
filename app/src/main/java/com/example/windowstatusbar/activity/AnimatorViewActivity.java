package com.example.windowstatusbar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.view.LoadingImageView;

public class AnimatorViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_view);
        LoadingImageView liv1 = findViewById(R.id.liv1);
        liv1.setMaskOrientation(LoadingImageView.MaskOrientation.LeftToRight);

        LoadingImageView liv2 = findViewById(R.id.liv2);
        liv2.setMaskOrientation(LoadingImageView.MaskOrientation.LeftToRight);

        LoadingImageView liv3 = findViewById(R.id.liv3);
        liv3.setMaskOrientation(LoadingImageView.MaskOrientation.LeftToRight);

        LoadingImageView liv4 = findViewById(R.id.liv4);
        liv4.setMaskOrientation(LoadingImageView.MaskOrientation.LeftToRight);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(v->{
            Intent intent = new Intent(AnimatorViewActivity.this,BrowserActivity.class);
            intent.putExtra("URL","http://m.ndlib.cn/03/html/index.html");
            startActivity(intent);
        });

    }
}
