package com.example.windowstatusbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windowstatusbar.popupwindow.DropdownMenu;
import com.example.windowstatusbar.popupwindow.listener.OnChooseListener;
import com.example.windowstatusbar.popupwindow.widget.DropListContent;
import com.example.windowstatusbar.popupwindow.widget.TextViewHeader;

import java.util.Arrays;

public class PopupActivity extends AppCompatActivity {

    private final String[] HEROES = {
            "Iron Man",
            "Ant Man",
            "American Captain",
            "Hulk",
            "Thor",
            "Black Widow",
            "一个长度特别长的用来测试最大长度的英雄"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        init();
    }

    private void init() {

        TextView tvChooseHero = findViewById(R.id.tv_hero);
        final TextView textContent = findViewById(R.id.textContent);


        // 默认样式 + 默认头部
        new DropdownMenu.Builder<String>()
                .header(new TextViewHeader(tvChooseHero))
                .content(new DropListContent(this, Arrays.asList(HEROES)))
                .build()
                .setOnChooseListener(new OnChooseListener<String>() {
                    @Override
                    public void onChoose(String result) {
                        textContent.setText(result);
                    }
                });

//        new DropdownMenu.Builder<String>()
//                .header(new AnimatedHeader(findViewById(R.id.layout_color_header)))
//                .content(new CustomListContent(this, Arrays.asList(COLORS)))
//                .build()
//                .setOnChooseListener(result -> {
//                    Toast.makeText(SampleActivity.this, result, Toast.LENGTH_SHORT).show();
//                    textContent.setText(result);
//                });
    }
}
