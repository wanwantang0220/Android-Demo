package com.example.windowstatusbar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    public abstract void bindView(ViewHolder holder, int position, View itemView);

    @Override
    public final void onBindViewHolder(final ViewHolder holder, int position) {
        final View inflate = holder.mItemView;
        bindView(holder, position, inflate);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void handleClick(ViewHolder holder) {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View mItemView;
        private BaseAdapter mAdapter;

        public ViewHolder(BaseAdapter adapter, View itemView) {
            super(itemView);
            mItemView = itemView;
            mAdapter = adapter;
            mAdapter.handleClick(this);
            if (mAdapter.mHandleClickListener != null) {
                mAdapter.mHandleClickListener.handleClick(this);
            }
        }

        public View getItemView() {
            return mItemView;
        }

        public BaseAdapter getAdapter() {
            return mAdapter;
        }
    }


    private HandleClickListener mHandleClickListener;

    public interface HandleClickListener {
        void handleClick(ViewHolder holder);
    }

    public void setHandleClickListener(HandleClickListener handleClickListener) {
        mHandleClickListener = handleClickListener;
    }
}
