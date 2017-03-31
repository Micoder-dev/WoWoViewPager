package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoPathAnimation;
import com.nightonke.wowoviewpager.WoWoPathView;

public class WoWoPathAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_path_animation;
    }

    @Override
    protected int fragmentNumber() {
        return 2;
    }

    @Override
    protected Integer[] fragmentColorsRes() {
        return new Integer[]{R.color.red, R.color.blue_5};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WoWoPathView pathView = (WoWoPathView) findViewById(R.id.path_view);

        // For different screen size, try to adjust the scale values to see the airplane.
        float xScale = 1;
        float yScale = 1;

        pathView.newPath()
                .pathMoveTo(xScale * screenW / 2, screenH + 100)
                .pathCubicTo(xScale * 313, yScale * (screenH - 531),
                        xScale * (-234), yScale * (screenH -644),
                        xScale * 141, yScale * (screenH - 772))
                .pathCubicTo(xScale * 266, yScale * (screenH - 817),
                        xScale * 444, yScale * (screenH - 825),
                        xScale * 596, yScale * (screenH - 788))
                .pathCubicTo(xScale * 825, yScale * (screenH - 727),
                        xScale * 755, yScale * (screenH - 592),
                        -100, yScale * (screenH - 609));

        ViewAnimation animation = new ViewAnimation(pathView);
        animation.add(WoWoPathAnimation.builder().page(0).start(0).end(1).path(pathView).build());
        wowo.addAnimation(animation);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
