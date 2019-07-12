package com.example.windowstatusbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windowstatusbar.adapter.RecyclerViewAdapter;
import com.example.windowstatusbar.adapter.StockAdapter;
import com.example.windowstatusbar.bean.StickyHeadEntity;
import com.example.windowstatusbar.bean.StockEntity;
import com.example.windowstatusbar.callback.OnItemClickListener;
import com.example.windowstatusbar.wave.DividerHelper;
import com.example.windowstatusbar.wave.OnStickyChangeListener;
import com.example.windowstatusbar.wave.StickyHeadContainer;
import com.example.windowstatusbar.wave.StickyItemDecoration;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 带粘性的recycleview  ,一级标题悬浮
 */
public class WaveStockActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StockAdapter mAdapter;
    private int mStickyPosition;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_stock);

        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                initView();
            }

            @Override
            protected String doInBackground(Void... voids) {
                return getStrFromAssets(WaveStockActivity.this, "rasking.json");
            }

            @Override
            protected void onPostExecute(String result) {
                parseAndSetData(result);
            }

        }.execute();

    }


    private void initView() {

        final StickyHeadContainer container = findViewById(R.id.shc);
        final TextView tvStockName = container.findViewById(R.id.tv_stock_name);
        final CheckBox checkBox = container.findViewById(R.id.checkbox);
        final ImageView more = container.findViewById(R.id.iv_more);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mAdapter.getData().get(mStickyPosition).getData().check = isChecked;
            mAdapter.notifyItemChanged(mStickyPosition);
        });

        container.setDataCallback(pos -> {
            mStickyPosition = pos;
            StockEntity.StockInfo item = mAdapter.getData().get(pos).getData();
            tvStockName.setText(item.stickyHeadName);
            checkBox.setChecked(item.check);
        });

        more.setOnClickListener(v -> Toast.makeText(WaveStockActivity.this, "点击了粘性头部的更多", Toast.LENGTH_SHORT).show());

        container.setOnClickListener(v -> Toast.makeText(WaveStockActivity.this, "点击了粘性头部：" + tvStockName.getText(), Toast.LENGTH_SHORT).show());

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(WaveStockActivity.this, LinearLayoutManager.VERTICAL, false));
        StickyItemDecoration stickyItemDecoration = new StickyItemDecoration(container, RecyclerViewAdapter.TYPE_STICKY_HEAD);
        stickyItemDecoration.setOnStickyChangeListener(new OnStickyChangeListener() {
            @Override
            public void onScrollable(int offset) {
                container.scrollChild(offset);
                container.setVisibility(View.VISIBLE);
            }

            @Override
            public void onInVisible() {
                container.reset();
                container.setVisibility(View.INVISIBLE);
            }
        });
        mRecyclerView.addItemDecoration(stickyItemDecoration);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(mRecyclerView.getContext()));

        mAdapter = new StockAdapter(null);
        mAdapter.setItemClickListener((view, data, position) -> Toast.makeText(WaveStockActivity.this, "点击了Item", Toast.LENGTH_SHORT).show());
    }

    /**
     * @return Json数据（String）
     * @description 通过assets文件获取json数据，这里写的十分简单，没做循环判断。
     */
    public static String getStrFromAssets(Context context, String name) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    private void parseAndSetData(String result) {
        Gson gson = new Gson();

        final StockEntity stockEntity = gson.fromJson(result, StockEntity.class);

        List<StockEntity.StockInfo> data = new ArrayList<>();

        data.add(new StockEntity.StockInfo(RecyclerViewAdapter.TYPE_STICKY_HEAD, "涨幅榜"));
        for (StockEntity.StockInfo info : stockEntity.increase_list) {
            info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            data.add(info);
        }

        data.add(new StockEntity.StockInfo(RecyclerViewAdapter.TYPE_STICKY_HEAD, "跌幅榜"));
        for (StockEntity.StockInfo info : stockEntity.down_list) {
            info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            data.add(info);
        }

        data.add(new StockEntity.StockInfo(RecyclerViewAdapter.TYPE_STICKY_HEAD, "换手率"));
        for (StockEntity.StockInfo info : stockEntity.change_list) {
            info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            data.add(info);
        }

        data.add(new StockEntity.StockInfo(RecyclerViewAdapter.TYPE_STICKY_HEAD, "振幅榜"));
        for (StockEntity.StockInfo info : stockEntity.amplitude_list) {
            info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            data.add(info);
        }

        List<StickyHeadEntity<StockEntity.StockInfo>> list = new ArrayList<>(data.size());
        list.add(new StickyHeadEntity<StockEntity.StockInfo>(null, StockAdapter.TYPE_HEAD, null));
        for (StockEntity.StockInfo info : data) {
            list.add(new StickyHeadEntity<>(info, info.getItemType(), info.stickyHeadName));
        }

        mAdapter.setData(list);
        mRecyclerView.setAdapter(mAdapter);

    }


    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int[] ATTRS = new int[]{android.R.attr.listDivider};

        private Drawable mDivider;

        public SpaceItemDecoration(Context context) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                DividerHelper.drawBottomAlignItem(c, mDivider, child, params);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int type = parent.getAdapter().getItemViewType(parent.getChildAdapterPosition(view));
            if (type != RecyclerViewAdapter.TYPE_DATA && type != RecyclerViewAdapter.TYPE_SMALL_STICKY_HEAD_WITH_DATA) {
                outRect.set(0, 0, 0, 0);
            } else {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}
