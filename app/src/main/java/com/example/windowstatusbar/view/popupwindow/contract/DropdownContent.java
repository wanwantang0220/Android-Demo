package com.example.windowstatusbar.view.popupwindow.contract;

import android.view.View;

import com.example.windowstatusbar.view.popupwindow.listener.OnChooseListener;


public interface DropdownContent<T> {
    View onCreateDropdownView(OnChooseListener<T> listener);
}
