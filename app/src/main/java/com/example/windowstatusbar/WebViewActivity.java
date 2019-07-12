package com.example.windowstatusbar;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.windowstatusbar.util.AndroidtoJs;

import butterknife.BindView;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;

    TextView tvShow;
    TextView tvBtn;

    JavaMethod javaMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        tvShow = findViewById(R.id.tvShow);
        webView = findViewById(R.id.webView);
        tvBtn = findViewById(R.id.tvBtn);
        WebSettings webSettings = webView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        webView.addJavascriptInterface(new JavaMethod(), "test");//AndroidtoJS类对象映射到js的test对象

        // 加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webView.loadUrl("file:///android_asset/JsMethod.html");

        String p = "hello";
        tvBtn.setOnClickListener(v -> webView.loadUrl("javascript:testAndroid('" + p + "')"));
    }

    public void setTextShow(String str) {
        tvShow.setText(str);
    }


    public class JavaMethod {

//        @JavascriptInterface
//        public void JsToJavaInterface(final String param) {
//            setTextShow("from JavaInterface: " + param);
//        }

        @JavascriptInterface
        public void hello(String param) {
            setTextShow("from JavaInterface: " + param);
        }
    }
}
