package com.example.windowstatusbar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.view.RecyclemIndicatorViewHolder;

import java.util.ArrayList;

public class RecyclemIndicatorAdapter extends RecyclerView.Adapter<RecyclemIndicatorViewHolder> {

    private Context mContext;
    private ArrayList<Integer> mList;
    private LayoutInflater mInflater;

    public RecyclemIndicatorAdapter(Context context, ArrayList<Integer> list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclemIndicatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = mInflater.inflate(R.layout.recyclem_indicator_item, parent, false);
        return new RecyclemIndicatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclemIndicatorViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageResource(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
