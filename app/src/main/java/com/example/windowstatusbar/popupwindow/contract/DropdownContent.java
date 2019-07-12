package com.example.windowstatusbar.popupwindow.contract;

import android.view.View;

import com.example.windowstatusbar.popupwindow.listener.OnChooseListener;


public interface DropdownContent<T> {
    View onCreateDropdownView(OnChooseListener<T> listener);
}
