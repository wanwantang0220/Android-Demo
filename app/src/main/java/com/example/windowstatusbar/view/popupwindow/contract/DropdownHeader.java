package com.example.windowstatusbar.view.popupwindow.contract;


import com.example.windowstatusbar.view.popupwindow.listener.OnChooseListener;
import com.example.windowstatusbar.view.popupwindow.listener.OnShowListener;
import com.example.windowstatusbar.view.popupwindow.listener.OnStateChangeListener;

public interface DropdownHeader<T> extends OnChooseListener<T>, OnStateChangeListener {

    void setupShowListener(OnShowListener onShowListener);
}
