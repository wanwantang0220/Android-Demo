package com.example.windowstatusbar.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.fragment.VideoItemFragment;
import com.example.windowstatusbar.widget.CirclePageIndicator;
import com.example.windowstatusbar.widget.ExtendedViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity  implements ViewPager.OnPageChangeListener{


    @BindView(R.id.vp_video)
    ExtendedViewPager vpVideo;
    @BindView(R.id.view_pager_indicator)
    CirclePageIndicator viewPagerIndicator;
    @BindView(R.id.tv_enter)
    TextView tvEnter;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpVideo.setAdapter(viewPagerAdapter);
        vpVideo.setOffscreenPageLimit(4);
        viewPagerIndicator.setViewPager(vpVideo);
        vpVideo.addOnPageChangeListener(this);


    }

    @Override
    public void onPageScrolled(int position, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        ((VideoItemFragment) (viewPagerAdapter.getItem(position))).play();

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @OnClick(R.id.tv_enter)
    public void onViewClicked() {
        startActivity(new Intent(SplashActivity.this, CardViewActivity.class));
    }

    public void next(int position) {
        int i = this.vpVideo.getCurrentItem();
        if (position == i) {
            position += 1;
            this.vpVideo.setCurrentItem(position, true);
        }
    }
    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final int[] videoRes; //视频资源
        private final int[] imgRes; //图片资源

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.videoRes = new int[]{R.raw.splash_1, R.raw.splash_2, R.raw.splash_3, R.raw.splash_4};
            this.imgRes = new int[]{R.drawable.slogan_1, R.drawable.slogan_2, R.drawable.slogan_3, R.drawable.slogan_4};
        }

        @Override
        public Fragment getItem(int position) {
            if (position < getCount()) {
                return VideoItemFragment.newInstance(position, videoRes[position], imgRes[position]);
            }
            throw new RuntimeException("Position out of range. Adapter has " + getCount() + " items");
        }

        @Override
        public int getCount() {
            return this.videoRes.length;
        }
    }

}
