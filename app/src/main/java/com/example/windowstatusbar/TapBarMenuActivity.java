package com.example.windowstatusbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.windowstatusbar.tapbarmenu.TapBarMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TapBarMenuActivity extends AppCompatActivity {

    @BindView(R.id.tapBarMenu)
    TapBarMenu tapBarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_bar);
        ButterKnife.bind(this);
    }

    int pos;

    @OnClick({R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.tapBarMenu})
    public void onMenuItemClick(View view) {
        switch (view.getId()) {
            case R.id.item1:
                Log.i("TAG", "Item 1 selected");
                pos = 1;
                tapBarMenu.close();
                break;
            case R.id.item2:
                Log.i("TAG", "Item 2 selected");
                tapBarMenu.close();
                pos = 2;
                break;
            case R.id.item3:
                Log.i("TAG", "Item 3 selected");
                tapBarMenu.close();
                pos = 3;
                break;
            case R.id.item4:
                Log.i("TAG", "Item 4 selected");
                tapBarMenu.close();
                pos = 4;
                break;
            case R.id.tapBarMenu:
                tapBarMenu.toggle();
                break;
            default:
                Log.i("TAG", "default  selected");
                tapBarMenu.close();
                pos = 0;
                break;
        }
        Log.i("TAG", "pos = " + pos);

    }
}
