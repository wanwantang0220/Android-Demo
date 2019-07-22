package com.example.windowstatusbar.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.windowstatusbar.R;

public class CommonViewHolder extends RecyclerView.ViewHolder {

    public TextView tv;


    public CommonViewHolder(@NonNull View itemView) {
        super(itemView);

        tv = itemView.findViewById(R.id.item_tv);
    }
}
