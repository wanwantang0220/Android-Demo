package com.example.windowstatusbar;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initShareSdk();
    }

    private void initShareSdk() {
        UMConfigure.init(this,"5cdb6eb24ca3577cf4000d92","umeng",UMConfigure.DEVICE_TYPE_PHONE,null);//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        //开启debug
        UMConfigure.setLogEnabled(true);


    }
}
