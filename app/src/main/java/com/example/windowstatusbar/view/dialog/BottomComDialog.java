package com.example.windowstatusbar.view.dialog;

import android.content.Context;
import android.support.v7.widget.OrientationHelper;

import java.util.List;

public class BottomComDialog {
    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;
    public static final int VERTICAL = OrientationHelper.VERTICAL;

    public static final int LINEAR = 0;
    public static final int GRID = 1;

    private CustomDialog customDialog;

    public BottomComDialog(Context context) {
        customDialog = new CustomDialog(context);
    }

    public BottomComDialog title(String title) {
        customDialog.title(title);
        return this;
    }

    public BottomComDialog title(int title) {
        customDialog.title(title);
        return this;
    }

    public BottomComDialog background(int res) {
        customDialog.background(res);
        return this;
    }

    public BottomComDialog inflateMenu(int menu, OnItemClickListener onItemClickListener) {
        customDialog.inflateMenu(menu, onItemClickListener);
        return this;
    }

    public BottomComDialog layout(int layout) {
        customDialog.layout(layout);
        return this;
    }

    public BottomComDialog orientation(int orientation) {
        customDialog.orientation(orientation);
        return this;
    }

    public BottomComDialog addItems(List<Item> items, OnItemClickListener onItemClickListener) {
        customDialog.addItems(items, onItemClickListener);
        return this;
    }

    /**
     * @deprecated
     */
    public BottomComDialog itemClick(OnItemClickListener listener) {
        customDialog.setItemClick(listener);
        return this;
    }

    public void show() {
        customDialog.show();
    }





}
