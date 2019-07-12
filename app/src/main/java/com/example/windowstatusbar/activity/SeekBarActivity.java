package com.example.windowstatusbar.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.SeekBar;

import com.example.windowstatusbar.R;
import com.example.windowstatusbar.view.BubbleSeekBar;
import com.example.windowstatusbar.view.RulerSeekBar;

public class SeekBarActivity extends Activity {

    SeekBar seekBar;
    BubbleSeekBar bubbleSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);

        seekBar = findViewById(R.id.sensor_sensitivity);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                int seekProgress = seekBar.getProgress();
                if (seekProgress < 13) {
                    seekBar.setProgress(0);
                } else if (seekProgress >= 13 && seekProgress < 38) {
                    seekBar.setProgress(25);
                } else if (seekProgress >= 38 && seekProgress < 63) {
                    seekBar.setProgress(50);
                } else if (seekProgress >= 63 && seekProgress < 88) {
                    seekBar.setProgress(75);
                } else if (seekProgress >= 88) {
                    seekBar.setProgress(100);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        bubbleSeekBar = findViewById(R.id.bsb);
        bubbleSeekBar.getConfigBuilder()
                .min(0)
                .max(5)
                .progress(2)
                .sectionCount(5)
                .trackColor(ContextCompat.getColor(this, R.color.color_gray))
                .secondTrackColor(ContextCompat.getColor(this, R.color.color_blue))
                .thumbColor(ContextCompat.getColor(this, R.color.color_blue))
                .showSectionText()
                .sectionTextColor(ContextCompat.getColor(this, R.color.page3_start_color))
                .sectionTextSize(18)
                .showThumbText()
                .touchToSeek()
                .seekStepSection()
//                .thumbTextColor(ContextCompat.getColor(this, R.color.color_red))
//                .thumbTextSize(18)
//                .bubbleColor(ContextCompat.getColor(this, R.color.color_red))
//                .bubbleTextSize(18)
//                .showSectionMark()
                .seekBySection()
//                .autoAdjustSectionMark()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();
    }
}
