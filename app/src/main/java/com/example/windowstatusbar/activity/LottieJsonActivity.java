package com.example.windowstatusbar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.example.windowstatusbar.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.windowstatusbar.activity.WaveStockActivity.getStrFromAssets;

public class LottieJsonActivity extends AppCompatActivity {


    @BindView(R.id.animation_view)
    LottieAnimationView  animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_json);
        ButterKnife.bind(this);

        animationView.setAnimation("progress-bar.json");//在assets目录下的动画json文件名。
        animationView.loop(true);//设置动画循环播放
        animationView.setImageAssetsFolder("images/");//assets目录下的子目录，存放动画所需的图片
        animationView.playAnimation();//播放动画
    }
}
