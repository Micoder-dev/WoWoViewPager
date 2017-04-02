package com.nightonke.wowoviewpager.Enum;

import android.view.animation.Interpolator;

/**
 * Created by Weiping Huang at 15:04 on 2017/3/31
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Class Gearbox changes of the WoWoViewPager.
 */

public enum Gearbox {

    ZERO(0),
    Positive1(1),
    Positive2(2),
    Positive3(3),
    Positive4(4),
    Positive5(5),  // Default value as ViewPager
    Positive6(6),
    Positive7(7);

    private int v;

    Gearbox(int v) {
        this.v = v;
    }

    public int value() {
        return v;
    }

    public Interpolator interpolator() {
        return new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                if (v == 0) return input;
                else if (v > 0) return (float) (1 - Math.pow(1 - input, v));
                else return (float) Math.pow(input, -v);
            }
        };
    }

}
