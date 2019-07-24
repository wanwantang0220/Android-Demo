package com.example.windowstatusbar.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.activity.SplashActivity;
import com.example.windowstatusbar.widget.FullScreenVideoView;


public class VideoItemFragment extends Fragment implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private int position;
    private int videoRes;
    private int imgRes;
    private int mVideoPosition;
    private FullScreenVideoView mVideoView;
    private boolean mHasPaused;
    protected boolean isInit = false;
    protected boolean isLoad = false;


    public static VideoItemFragment newInstance(int position, int videoRes, int imgRes) {
        VideoItemFragment fragment = new VideoItemFragment();
        Bundle localBundle = new Bundle();
        localBundle.putInt("position", position);
        localBundle.putInt("videoRes", videoRes);
        localBundle.putInt("imgRes", imgRes);
        fragment.setArguments(localBundle);
        return fragment;
    }

    public VideoItemFragment() {
        mHasPaused = false;
        mVideoPosition = 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_video_item, container, false);
        isInit = true;
        /**初始化的时候去加载数据**/
        isCanLoadData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }

        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }

    protected void lazyLoad() {
        position = getArguments().getInt("position");
        videoRes = getArguments().getInt("videoRes");
        imgRes = getArguments().getInt("imgRes");

        mVideoView =view.findViewById(R.id.vvSplash);
        ImageView mvSlogan = view.findViewById(R.id.ivSlogan);

        mVideoView.setOnErrorListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + videoRes);
        mvSlogan.setImageResource(imgRes);

    }


    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        FragmentActivity localFragmentActivity = getActivity();
        if (((localFragmentActivity instanceof SplashActivity))) {
            ((SplashActivity) localFragmentActivity).next(position);
        }
        return true;
    }

    private void stopLoad() {
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (mVideoView != null) {
            mVideoView.requestFocus();
            mVideoView.setOnCompletionListener(this);
            mVideoView.seekTo(0);
            mVideoView.start();
        }
        return;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        FragmentActivity localFragmentActivity = getActivity();
        if ((localFragmentActivity != null) && ((localFragmentActivity instanceof SplashActivity))) {
            ((SplashActivity) localFragmentActivity).next(position);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mHasPaused) {
            if (mVideoView != null) {
                mVideoView.seekTo(mVideoPosition);
                mVideoView.resume();
            }
        }
        return;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoPosition = mVideoView.getCurrentPosition();
        }
        mHasPaused = true;
    }

    public void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
        return;
    }

    public void pause() {
        if ((mVideoView != null) && (mVideoView.canPause())) {
            mVideoView.setOnCompletionListener(null);
            mVideoView.pause();
        }
        return;

    }

    public void play() {
        if (mVideoView != null) {
            mVideoView.requestFocus();
            mVideoView.setOnCompletionListener(this);
            mVideoView.seekTo(0);
        } else {
            return;
        }
        mVideoView.start();
    }

    public void reLoadVideo() {
        if (mVideoView != null) {
            mVideoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + videoRes);
        }
    }
}
