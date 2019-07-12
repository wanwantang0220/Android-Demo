package com.example.windowstatusbar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.windowstatusbar.module.GlideApp;


public class CardViewActivity extends AppCompatActivity {

    private Context mContext;
    ImageView iamgeView, iamgeView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        mContext = this;

        iamgeView = findViewById(R.id.iamgeView);
        iamgeView2 = findViewById(R.id.iamgeView2);

//        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.bg_girl);
//        RoundedBitmapDrawable roundedDrawable =
//                RoundedBitmapDrawableFactory.create(getResources(),bitmap);//传入bitmap
////设置圆角角度
//        roundedDrawable.setCornerRadius(30L);
//        iamgeView.setImageDrawable(roundedDrawable);


        String url = "http://pic22.nipic.com/20120727/9880981_174825125145_2.jpg";

        CornerTransform transformation = new CornerTransform(this, 30);
        //只是绘制左上角和右上角圆角
        transformation.setExceptCorner(true, true, true, true);
        RequestOptions options = new RequestOptions().placeholder(R.color.page3_start_color).transform(transformation);
//        GlideApp.with(this).asBitmap().load(R.mipmap.pic4).centerCrop().apply(options).into(iamgeView);


        RoundedCornersTransform transform = new RoundedCornersTransform(this, 30);
        transform.setNeedCorner(true, true, true, true);
        RequestOptions options2 = new RequestOptions().placeholder(R.color.page3_start_color).transform(transform);
//        GlideApp.with(this).asBitmap().load(R.mipmap.bg_monkey_king).apply(options2).into(iamgeView2);


//        GlideApp.with(this).asBitmap()
//                .load(R.mipmap.pic1)
////                .centerCrop()
////                .apply(RequestOptions.bitmapTransform(transform))
//                //设置等待时的图片
//                .placeholder(R.mipmap.ic_avatar)
//                //设置加载失败后的图片显示
//                .error(R.drawable.ic_launcher_background)
//                .fitCenter()
////                .override(500, 500)
//                .apply(options2)
//                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
//                .skipMemoryCache(false)
//                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                //设置图片加载的优先级
//                .priority(Priority.HIGH)
//                .into(iamgeView);


        GlideApp.with(this).asBitmap()
                .load(R.mipmap.bg_monkey_king)
                .placeholder(R.mipmap.ic_avatar)
                //设置加载失败后的图片显示
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .apply(options2)
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                .into(iamgeView);


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
