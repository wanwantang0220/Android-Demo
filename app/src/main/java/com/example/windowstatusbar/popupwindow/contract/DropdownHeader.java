package com.example.windowstatusbar.popupwindow.contract;


import com.example.windowstatusbar.popupwindow.listener.OnChooseListener;
import com.example.windowstatusbar.popupwindow.listener.OnShowListener;
import com.example.windowstatusbar.popupwindow.listener.OnStateChangeListener;

public interface DropdownHeader<T> extends OnChooseListener<T>, OnStateChangeListener {

    void setupShowListener(OnShowListener onShowListener);
}
