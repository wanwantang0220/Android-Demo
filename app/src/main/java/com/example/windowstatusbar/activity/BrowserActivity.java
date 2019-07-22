package com.example.windowstatusbar.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.view.LoadingImageView;

public class BrowserActivity extends AppCompatActivity {


    WebView webView;
    LoadingImageView liv1;
    private String url = "http://m.ndlib.cn/03/html/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x5_browser);
        webView = findViewById(R.id.web_view);
        liv1 = findViewById(R.id.liv1);
        liv1.setMaskOrientation(LoadingImageView.MaskOrientation.LeftToRight);

        Intent intent = getIntent();
        if (intent != null) {
            url = getIntent().getStringExtra("URL");
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                liv1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("TAG", "onPageFinished URL = " + url);
                liv1.setVisibility(View.GONE);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String loadUrl) {
//                webview自己处理
//                wvWebView.loadUrl(loadUrl);
//                return true;
                return false;//系统自己处理
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                if (request.isForMainFrame()) {
                    int errorCode = error.getErrorCode();
                    if (errorCode == ERROR_HOST_LOOKUP || errorCode == ERROR_CONNECT || errorCode == ERROR_TIMEOUT) {

                    }
                }
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                if (request.isForMainFrame()) {
                    int statusCode = errorResponse.getStatusCode();

                    if (404 == statusCode || 500 == statusCode) {

                    }
                }
            }
        });

        WebSettings webSettings = webView.getSettings();

        //设置WebView是否允许执行JavaScript脚本，默认false，不允许
        webSettings.setJavaScriptEnabled(true);
        //设置WebView是否使用其内置的变焦机制，该机制结合屏幕缩放控件使用，默认是false，不使用内置变焦机制
//        webSettings.setAllowContentAccess(true);
//        //设置是否开启数据库存储API权限，默认false，未开启，可以参考setDatabasePath(String path)
//        webSettings.setDatabaseEnabled(true);
//        //设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
//        webSettings.setDomStorageEnabled(true);
//        //设置Application缓存API是否开启，默认false，设置有效的缓存路径参考setAppCachePath(String path)方法
//        webSettings.setAppCacheEnabled(true);
//        //设置WebView是否保存表单数据，默认true，保存数据。
//        webSettings.setSaveFormData(false);
        //设置WebView是否使用viewport，当该属性被设置为false时，加载页面的宽度总是适应WebView控件宽度；
        // 当被设置为true，当前页面包含viewport属性标签，在标签中指定宽度值生效，如果页面不包含viewport标签，无法提供一个宽度值，这个时候该方法将被使用。
//        webSettings.setUseWideViewPort(true);
//        //设置WebView是否使用预览模式加载界面。
//        webSettings.setLoadWithOverviewMode(true);


        webView.loadUrl(url);

    }


    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroy();
    }
}
