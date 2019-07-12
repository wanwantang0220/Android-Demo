package com.example.windowstatusbar;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windowstatusbar.activity.PopupActivity;
import com.example.windowstatusbar.activity.SeekBarActivity;
import com.example.windowstatusbar.activity.ShareElementActivity;
import com.example.windowstatusbar.activity.ShareSdkActivity;
import com.example.windowstatusbar.activity.StatusBarActivity;
import com.example.windowstatusbar.activity.TabActivity;
import com.example.windowstatusbar.activity.TapBarMenuActivity;
import com.example.windowstatusbar.activity.TransitionActivity;
import com.example.windowstatusbar.activity.WaveSlideActivity;
import com.example.windowstatusbar.activity.WaveStockActivity;
import com.example.windowstatusbar.activity.WebViewActivity;
import com.example.windowstatusbar.activity.BannerActivity;
import com.example.windowstatusbar.activity.ButtomDialogActivity;
import com.example.windowstatusbar.activity.CardViewActivity;
import com.example.windowstatusbar.activity.GlideActivity;
import com.example.windowstatusbar.activity.ImageViewActivity;
import com.example.windowstatusbar.activity.MatisseActivity;
import com.example.windowstatusbar.activity.MyDialogActivity;
import com.example.windowstatusbar.arc_header_view.ArcHeaderActivity;
import com.example.windowstatusbar.arc_header_view.ArcHeaderImageActivity;
import com.example.windowstatusbar.util.AppConstant;

import java.util.ArrayList;
import java.util.List;

public class RecyclemAdapter extends RecyclerView.Adapter<RecyclemAdapter.ComViewHolder> {


    private Context mContext;
    private List<String> mList = new ArrayList<>();


    public RecyclemAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ComViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ComViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComViewHolder viewHolder, final int i) {
        viewHolder.tv.setText(mList.get(i));
        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mList.get(i);
                if (title.equals(AppConstant.LIST_TITLE1)) {
                    mContext.startActivity(new Intent(mContext, StatusBarActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE2)) {
                    mContext.startActivity(new Intent(mContext, ImageViewActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE3)) {
                    mContext.startActivity(new Intent(mContext, ArcHeaderActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE4)) {
                    mContext.startActivity(new Intent(mContext, ArcHeaderImageActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE5)) {
                    mContext.startActivity(new Intent(mContext, CardViewActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE6)) {
                    mContext.startActivity(new Intent(mContext, GlideActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE7)) {
                    mContext.startActivity(new Intent(mContext, MatisseActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE8)) {
                    mContext.startActivity(new Intent(mContext, TabActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE9)) {
                    mContext.startActivity(new Intent(mContext, PopupActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE10)) {
                    mContext.startActivity(new Intent(mContext, ButtomDialogActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE11)) {
                    mContext.startActivity(new Intent(mContext, BannerActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE12)) {
                    mContext.startActivity(new Intent(mContext, ShareElementActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE13)) {
                    mContext.startActivity(new Intent(mContext, SeekBarActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE14)) {
                    mContext.startActivity(new Intent(mContext, ImageCompressActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE15)) {
                    mContext.startActivity(new Intent(mContext, MyDialogActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE16)) {
                    mContext.startActivity(new Intent(mContext, MyDialogActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE17)) {
                    mContext.startActivity(new Intent(mContext, WebViewActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE18)) {
                    mContext.startActivity(new Intent(mContext, ShareSdkActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE19)) {
                    mContext.startActivity(new Intent(mContext, TransitionActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE20)) {
                    mContext.startActivity(new Intent(mContext, WaveSlideActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE21)) {
                    mContext.startActivity(new Intent(mContext, WaveStockActivity.class));
                } else if (title.equals(AppConstant.LIST_TITLE22)) {
                    mContext.startActivity(new Intent(mContext, TapBarMenuActivity.class));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setData(ArrayList<String> list) {
        mList.clear();
        if (list != null && list.size() > 0) {
            mList.addAll(list);
            notifyDataSetChanged();
        } else {
            notifyDataSetChanged();
        }
    }

    class ComViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ComViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvItem);
        }
    }
}
