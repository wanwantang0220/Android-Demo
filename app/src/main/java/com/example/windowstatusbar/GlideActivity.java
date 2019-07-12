package com.example.windowstatusbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.windowstatusbar.glide.RoundedCornersTransformation;
import com.example.windowstatusbar.module.GlideApp;


import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GlideActivity extends AppCompatActivity {


    private Context mContext;

    private ImageView mImgBase;
    private ImageView mImgOverride;
    private ImageView mImgCircleCrop;
    private ImageView mImgRound;
    private ImageView leftImg, rightTopImg, rightBottomImg;

    private ImageView leftImg1, rightTopImg1, rightBottomImg1;
    private ImageView cardImg;

    private String imgUrl = "https://pic.cnblogs.com/avatar/93830/20170607145247.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        mContext = this;

        initViews();
        initDatas();
    }

    private void initViews() {
        mImgBase = findViewById(R.id.img_base);
        mImgOverride = findViewById(R.id.img_override);
        mImgCircleCrop = findViewById(R.id.img_circleCrop);
        mImgRound = findViewById(R.id.img_round);
        leftImg = findViewById(R.id.leftImg);
        rightTopImg = findViewById(R.id.rightTopImg);
        rightBottomImg = findViewById(R.id.rightBottomImg);

        leftImg1 = findViewById(R.id.leftImg1);
        rightTopImg1 = findViewById(R.id.rightTopImg1);
        rightBottomImg1 = findViewById(R.id.rightBottomImg1);

        cardImg = findViewById(R.id.cardImg);
    }

    private void initDatas() {

        glideBase();
        glideOverride();
        glideCircleCrop();
        glideRound();
        glideThreeImg();
        glideCardView();
    }

    private void glideCardView() {
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.pic5);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.pic2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.mipmap.pic4);

        leftImg1.setImageBitmap(bitmap1);
        rightTopImg1.setImageBitmap(bitmap2);
        rightBottomImg1.setImageBitmap(bitmap3);


        cardImg.setImageBitmap(bitmap3);
    }


    //Glide的基础使用
    private void glideBase() {
        GlideApp.with(mContext)
                .load(R.mipmap.pic1)
                //设置等待时的图片
                .placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.img_error)
                .fitCenter()
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                .into(mImgBase);
    }

    //Glide重新改变图片大小
    private void glideOverride() {
        setColumnNumber(mContext, 3);//计算宽度和高度值（1：1.5或者1:1）
        GlideApp.with(mContext)
                .load(R.mipmap.pic2)
                //设置等待时的图片
                .placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.img_error)
                .centerCrop()
                .override(imageWidthSize, imageHeightSize)
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                .into(mImgOverride);
    }

    //用于计算图片的宽高值
    private int imageWidthSize;
    private int imageHeightSize;

    private void setColumnNumber(Context context, int columnNumber) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        imageWidthSize = widthPixels / columnNumber;
        imageHeightSize = (int) (imageWidthSize * 1.5);//长方形样式，二选一
        //imageHeightSize = imageWidthSize;//正方形样式，二选一
    }

    //Glide的圆形效果
    private void glideCircleCrop() {

        GlideApp.with(mContext)
                .load(R.mipmap.bg_girl)
                //设置等待时的图片【这个时候需要注释，否则这个会作为背景图】
                //.placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.img_error)
                .centerCrop()
                .override(imageWidthSize, imageHeightSize)
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                //圆形
                .circleCrop()
                .into(mImgCircleCrop);
    }

    //Glide的圆角效果
    private void glideRound() {
        GlideApp.with(mContext)
                .load(R.mipmap.pic5)
                //设置等待时的图片【这个时候需要注释，否则这个会作为背景图】
                .placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.img_error)
                .centerCrop()
                .override(imageWidthSize, imageHeightSize)
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                .transform(new RoundedCornersTransformation(dip2px(mContext, 50), 50, RoundedCornersTransformation.CornerType.LEFT))
                .into(mImgRound);
    }


    private void glideThreeImg() {
        GlideApp.with(mContext)
                .load(R.mipmap.pic5)
                //设置等待时的图片【这个时候需要注释，否则这个会作为背景图】
                .placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.img_error)
                .centerCrop()
                .override(imageWidthSize, imageHeightSize)
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                .transform(new RoundedCornersTransformation(dip2px(mContext, 15), 0, RoundedCornersTransformation.CornerType.LEFT))
                .into(leftImg);

        GlideApp.with(mContext)
                .load(R.mipmap.pic4)
                //设置等待时的图片【这个时候需要注释，否则这个会作为背景图】
                .placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.img_error)
                .centerCrop()
                .override(imageWidthSize, imageHeightSize)
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                .transform(new RoundedCornersTransformation(dip2px(mContext, 15), 0, RoundedCornersTransformation.CornerType.TOP_RIGHT))
                .into(rightTopImg);


        GlideApp.with(mContext)
                .load(R.mipmap.pic2)
                //设置等待时的图片【这个时候需要注释，否则这个会作为背景图】
                .placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.img_error)
                .fitCenter()
                .override(imageWidthSize, imageHeightSize)
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                .transform(new RoundedCornersTransformation(dip2px(mContext, 10), 0, RoundedCornersTransformation.CornerType.BOTTOM_RIGHT))
                .into(rightBottomImg);
    }

    /**
     * dp转px
     * 16dp - 48px
     * 17dp - 51px
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * scale) + 0.5f);
    }
}
