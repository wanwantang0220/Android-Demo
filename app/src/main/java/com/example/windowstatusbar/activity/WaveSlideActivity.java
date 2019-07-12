package com.example.windowstatusbar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.adapter.CityAdapter;
import com.example.windowstatusbar.bean.City;
import com.example.windowstatusbar.view.wave.LetterComparator;
import com.example.windowstatusbar.view.wave.PinnedHeaderDecoration;
import com.example.windowstatusbar.view.wave.WaveSideBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WaveSlideActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    WaveSideBarView mSideBarView;
    CityAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_slide);

        mRecyclerView = findViewById(R.id.recycler_view);
        mSideBarView = findViewById(R.id.side_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, (parent, adapterPosition) -> true);
        mRecyclerView.addItemDecoration(decoration);


        new Thread(() -> {
            Type listType = new TypeToken<ArrayList<City>>() {}.getType();
            Gson gson = new Gson();
            final List<City> list = gson.fromJson(City.DATA, listType);
            Collections.sort(list, new LetterComparator());
            runOnUiThread(() -> {
                adapter = new CityAdapter(WaveSlideActivity.this, list);
                mRecyclerView.setAdapter(adapter);
            });
        }).start();

        mSideBarView.setOnTouchLetterChangeListener(letter -> {
            int pos = adapter.getLetterPosition(letter);

            if (pos != -1) {
                mRecyclerView.scrollToPosition(pos);
                LinearLayoutManager mLayoutManager =
                        (LinearLayoutManager) mRecyclerView.getLayoutManager();
                Objects.requireNonNull(mLayoutManager).scrollToPositionWithOffset(pos, 0);
            }
        });
    }
}
