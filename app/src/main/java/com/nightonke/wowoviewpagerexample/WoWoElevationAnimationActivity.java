package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.widget.TextView;

public class WoWoElevationAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_elevation_animation;
    }

    @Override
    protected Integer[] fragmentColorsRes() {
        return new Integer[]{
                R.color.blue_1,
                R.color.blue_1,
                R.color.blue_1,
                R.color.blue_1,
                R.color.blue_1
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = (TextView) findViewById(R.id.test);
        wowo.addAnimation(text)
                .add(CustomAnimation.builder().page(0).from(0).to(20).build())
                .add(CustomAnimation.builder().page(1).from(20).to(40).build())
                .add(CustomAnimation.builder().page(2).from(40).to(0).build())
                .add(CustomAnimation.builder().page(3).from(0).to(60).build());
    }
}
