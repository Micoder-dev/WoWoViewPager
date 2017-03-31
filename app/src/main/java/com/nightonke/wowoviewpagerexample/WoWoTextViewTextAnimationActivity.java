package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTextViewTextAnimation;

public class WoWoTextViewTextAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_text_view_text_animation_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
        animation.add(WoWoTextViewTextAnimation.builder().page(0).start(0).end(1).from("Nightonke").to("WoWoViewPager").build());
        animation.add(WoWoTextViewTextAnimation.builder().page(1).start(0).end(1).from("WoWoViewPager").to("").build());
        animation.add(WoWoTextViewTextAnimation.builder().page(2).start(0).end(1).from("").to("Nightonke").build());
        animation.add(WoWoTextViewTextAnimation.builder().page(3).start(0).end(0.5).from("Nightonke").to("Huang").build());
        animation.add(WoWoTextViewTextAnimation.builder().page(3).start(0.5).end(1).from("Huang").to("Weiping").build());
        wowo.addAnimation(animation);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
