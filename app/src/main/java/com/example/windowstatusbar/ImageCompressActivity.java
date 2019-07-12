package com.example.windowstatusbar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.windowstatusbar.module.GlideApp;

import net.bither.util.CompressTools;
import net.bither.util.FileUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

import static net.bither.util.FileUtil.getReadableFileSize;


public class ImageCompressActivity extends Activity {

    @BindView(R.id.main_image_old)
    ImageView mainImageOld;
    @BindView(R.id.main_text_old)
    TextView mainTextOld;
    @BindView(R.id.main_image_new)
    ImageView mainImageNew;
    @BindView(R.id.main_text_new)
    TextView mainTextNew;
    private String url = "https://yunlin-oss.oss-cn-hangzhou.aliyuncs.com/yunlin/20190308/d3acb8a4227841228621783d6900139f.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_compress);

        ButterKnife.bind(this);


        GlideApp.with(this).load(url)
                .into(mainImageOld);

    }

    public void compress(View view) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_girl);

        GlideApp.with(this).asBitmap().load(url).into(target);
    }


    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {

            CompressTools.newBuilder(ImageCompressActivity.this)
                    // .setMaxWidth(1280) // 默认最大宽度为720
                    // .setMaxHeight(850) // 默认最大高度为960
                    .setQuality(80) // 默认压缩质量为60,60足够清晰
                    .setBitmapFormat(Bitmap.CompressFormat.JPEG) // 设置默认压缩为jpg格式
                    .setKeepResolution(false)// 设置保持原图分辨率，则设置的最大宽高就无效了。不需要设置最大宽高了。设置也不会报错了，该参数默认false
                    .setFileName("test123").setDestinationDirectoryPath(FileUtil.getPhotoFileDir().getAbsolutePath()).build()
                    .compressToFile(bitmap, new CompressTools.OnCompressListener<File>() {
                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onFail(String error) {

                        }

                        @Override
                        public void onSuccess(File file) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            mainImageNew.setImageBitmap(bitmap);
                            mainTextNew.setText(String.format("Size : %s", getReadableFileSize(file.length())));
                        }
                    });

        }
    };
}
