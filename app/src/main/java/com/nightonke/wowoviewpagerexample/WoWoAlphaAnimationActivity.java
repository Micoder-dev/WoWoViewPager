package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoAlphaAnimation;

public class WoWoAlphaAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_alpha_animation;
    }

    @Override
    protected Integer[] fragmentColorsRes() {
        return new Integer[]{R.color.white, R.color.white, R.color.white, R.color.white, R.color.white};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
        animation.add(WoWoAlphaAnimation.builder().page(0).start(0).end(1).from(1).to(0.5).build());
        animation.add(WoWoAlphaAnimation.builder().page(1).start(0).end(1).from(0.5).to(1).build());
        animation.add(WoWoAlphaAnimation.builder().page(2).start(0).end(0.5).from(1).to(0).build());
        animation.add(WoWoAlphaAnimation.builder().page(2).start(0.5).end(1).from(0).to(1).build());
        animation.add(WoWoAlphaAnimation.builder().page(3).start(0).end(0.5).from(1).to(0.3).build());
        animation.add(WoWoAlphaAnimation.builder().page(3).start(0.5).end(1).from(0.3).to(1).build());
        wowo.addAnimation(animation);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
