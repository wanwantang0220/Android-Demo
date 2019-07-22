package com.example.windowstatusbar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.view.CommonViewHolder;

import java.util.ArrayList;

public class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private ArrayList<String> mList;
    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener = null;

    public CommonAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = mInflater.inflate(R.layout.common_item, parent, false);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder viewHolder, int position) {
        viewHolder.tv.setText(mList.get(position));
        viewHolder.tv.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
