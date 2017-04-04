package com.nightonke.wowoviewpager.Animation;

import android.os.Build;
import android.view.View;

import com.nightonke.wowoviewpager.Enum.Ease;

/**
 * Created by Weiping Huang at 14:08 on 2017/4/4
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change 3D-position of a view.
 */

public class WoWoTranslation3DAnimation extends XYZPageAnimation {

    public WoWoTranslation3DAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, float fromX, float fromY, float fromZ, float toX, float toY, float toZ) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack, fromX, fromY, fromZ, toX, toY, toZ);
    }

    @Override
    protected void toStartState(View view) {
        view.setX(fromX);
        view.setY(fromY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) view.setTranslationZ(fromZ);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        view.setX(fromX + (toX - fromX) * offset);
        view.setY(fromY + (toY - fromY) * offset);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) view.setTranslationZ(fromZ + (toZ - fromZ) * offset);
    }

    @Override
    protected void toEndState(View view) {
        view.setX(toX);
        view.setY(toY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) view.setTranslationZ(toZ);
    }

    public static class Builder extends XYZPageAnimation.Builder<WoWoPosition3DAnimation.Builder> {

        public WoWoTranslation3DAnimation build() {
            checkUninitializedAttributes();
            return new WoWoTranslation3DAnimation(page, startOffset, endOffset, ease, useSameEaseEnumBack, fromX, fromY, fromZ, toX, toY, toZ);
        }
    }
}