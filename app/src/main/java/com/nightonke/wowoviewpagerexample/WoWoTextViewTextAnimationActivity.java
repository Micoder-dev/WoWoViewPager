package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.view.View;

import com.nightonke.wowoviewpager.Animation.WoWoTextViewTextAnimation;
import com.nightonke.wowoviewpager.Enum.Typewriter;

public class WoWoTextViewTextAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_text_view_text_animation_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addTextAnimation(findViewById(R.id.test0), Typewriter.InsertFromLeft);
        addTextAnimation(findViewById(R.id.test1), Typewriter.DeleteThenType);
        addTextAnimation(findViewById(R.id.test2), Typewriter.InsertFromRight);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }

    private void addTextAnimation(View view, Typewriter typewriter) {
        wowo.addAnimation(view)
                .add(WoWoTextViewTextAnimation.builder().page(0).start(0).end(1)
                        .from("Nightonke").to("WoWoViewPager").typewriter(typewriter).build())
                .add(WoWoTextViewTextAnimation.builder().page(1).start(0).end(1)
                        .from("WoWoViewPager").to("").typewriter(typewriter).build())
                .add(WoWoTextViewTextAnimation.builder().page(2).start(0).end(1)
                        .from("").to("Nightonke").typewriter(typewriter).build())
                .add(WoWoTextViewTextAnimation.builder().page(3).start(0).end(0.5)
                        .from("Nightonke").to("Huang").typewriter(typewriter).build())
                .add(WoWoTextViewTextAnimation.builder().page(3).start(0.5).end(1)
                        .from("Huang").to("Weiping").typewriter(typewriter).build());
    }
}