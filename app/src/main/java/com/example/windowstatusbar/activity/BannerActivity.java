package com.example.windowstatusbar.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.windowstatusbar.callback.GlideImageLoader;
import com.example.windowstatusbar.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {

    public static final int []RES = new int[]{R.mipmap.image5,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image6,R.mipmap.image7,R.mipmap.image8};

    Banner mBanner;

    private List<String> imageTitle;
    private List<Bitmap> imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mBanner = findViewById(R.id.banner);


        //设置图片加载集合
        imageArray=new ArrayList<>();

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.mipmap.image1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.image2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),R.mipmap.image3);
        imageArray.add(bitmap1);
        imageArray.add(bitmap2);
        imageArray.add(bitmap3);


        //设置图片标题集合
        imageTitle=new ArrayList<>();
        imageTitle.add("aaaaaaaaa");
        imageTitle.add("bbbbbbbbb");
        imageTitle.add("ccccccccc");

        mBanner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(imageArray);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.CubeIn);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(imageTitle);
        //设置轮播时间
        mBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }
}
