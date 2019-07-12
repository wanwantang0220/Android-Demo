package com.example.windowstatusbar.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.view.dialog.MyDialog;

import java.util.ArrayList;

public class MyDialogActivity extends Activity {

    private MyDialog dialog;
    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);


        mList.add("整租");
        mList.add("合租");
        dialog = new MyDialog(this, mList, new MyDialog.OnSelectorListener() {
            @Override
            public void selectPosition(int position) {
                Toast.makeText(MyDialogActivity.this, mList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cancel() {

            }
        });
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


    }
}
