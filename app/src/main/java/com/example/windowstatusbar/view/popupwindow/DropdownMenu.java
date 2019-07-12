package com.example.windowstatusbar.view.popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.view.popupwindow.contract.DropdownContent;
import com.example.windowstatusbar.view.popupwindow.contract.DropdownHeader;
import com.example.windowstatusbar.view.popupwindow.listener.OnChooseListener;


public class DropdownMenu<T> {

    private PopupWindow mPopupWindow;

    private OnChooseListener<T> mOnChooseListener;

    public static class Builder<T> {
        private DropdownHeader<T> header;
        private DropdownContent<T> content;

        public Builder<T> header(DropdownHeader<T> header) {
            this.header = header;
            return this;
        }

        public Builder<T> content(DropdownContent<T> content) {
            this.content = content;
            return this;
        }

        public DropdownMenu<T> build() {
            return new DropdownMenu<>(header, content);
        }
    }

    private DropdownMenu(DropdownHeader<T> header, DropdownContent<T> content) {

        OnChooseListener<T> onChooseListener = new OnChooseListener<T>() {
            @Override
            public void onChoose(T result) {
                header.onChoose(result);
                mPopupWindow.dismiss();
                mOnChooseListener.onChoose(result);
            }
        };

        View dropdownContent = content.onCreateDropdownView(onChooseListener);
        LinearLayout container = wrapShadowContainer(dropdownContent);

        mPopupWindow = new PopupWindow(
                container,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );

        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(() -> header.onChange(false));
        mPopupWindow.setAnimationStyle(R.style.popmenu_animation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        mPopupWindow.setBackgroundDrawable(dw);


        header.setupShowListener(v -> {
            header.onChange(true);
            mPopupWindow.showAsDropDown(v);
        });
    }

    public void setOnChooseListener(OnChooseListener<T> onChooseListener) {
        mOnChooseListener = onChooseListener;
    }

    private LinearLayout wrapShadowContainer(View dropdownContent) {
        LinearLayout container = new LinearLayout(dropdownContent.getContext());
        container.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        container.setOrientation(LinearLayout.VERTICAL);
        container.addView(dropdownContent);

        View shadowView = new View(dropdownContent.getContext());
        shadowView.setBackgroundResource(R.drawable.ddm_shadow);
        shadowView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                Utils.dp2px(16)
        ));

        container.addView(shadowView);
        return container;
    }
}
