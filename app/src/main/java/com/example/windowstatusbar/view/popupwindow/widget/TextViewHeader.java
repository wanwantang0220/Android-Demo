package com.example.windowstatusbar.view.popupwindow.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.view.popupwindow.contract.DropdownHeader;
import com.example.windowstatusbar.view.popupwindow.listener.OnShowListener;


public class TextViewHeader implements DropdownHeader<String> {

    private final TextView mTextView;

    public TextViewHeader(TextView textView) {
        mTextView = textView;
    }

    @Override
    public void onChoose(String text) {
        mTextView.setText(text);
    }

    @Override
    public void onChange(boolean isExpand) {
        Context context = mTextView.getContext();

        Resources resources = context.getResources();
        Drawable upIcon = resources.getDrawable(R.drawable.ddm_ic_arrow_up);

        Drawable downIcon = resources.getDrawable(R.drawable.ddm_ic_arrow_down);

        Drawable indicator = isExpand ? upIcon : downIcon;
        mTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, indicator, null);
    }

    @Override
    public void setupShowListener(OnShowListener onShowListener) {
        mTextView.setOnClickListener(onShowListener::onShow);
    }
}
