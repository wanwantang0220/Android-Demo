package com.example.windowstatusbar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.view.dialog.BottomComDialog;
import com.example.windowstatusbar.view.dialog.Item;
import com.example.windowstatusbar.view.dialog.OnItemClickListener;

public class ButtomDialogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom_dialog);

        Button btn = findViewById(R.id.btnDialog);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        findViewById(R.id.btnDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomComDialog(ButtomDialogActivity.this)
                        // .title("share_title")
                        .orientation(BottomComDialog.VERTICAL)
                        .inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                            @Override
                            public void click(Item item) {
                                Toast.makeText(ButtomDialogActivity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
