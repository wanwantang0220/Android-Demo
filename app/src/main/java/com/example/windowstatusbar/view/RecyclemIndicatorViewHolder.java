package com.example.windowstatusbar.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.windowstatusbar.R;

public class RecyclemIndicatorViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public RecyclemIndicatorViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.iamgeViewIndicator);

    }
}
