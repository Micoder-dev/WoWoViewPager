package com.nightonke.wowoviewpager.Animation;

import com.nightonke.wowoviewpager.Enum.Chameleon;
import com.nightonke.wowoviewpager.Enum.Ease;

/**
 * Created by Weiping Huang at 09:49 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

abstract class SingleColorPageAnimation extends PageAnimation {

    Integer fromColor = null;
    Integer toColor = null;
    Chameleon chameleon = Chameleon.RGB;

    private int[] fromARGBArray = null;
    private int[] toARGBArray = null;
    private float[] fromHSVArray = null;
    private float[] toHSVArray = null;

    /**
     * Construct a page animation for a single color translation.
     *
     * @param page The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset The animation only plays when the offset of page is large than startOffset.
     * @param endOffset The animation only plays when the offset of page is less than endOffset.
     * @param ease The ease type of the animation.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromColor The starting-color.
     * @param toColor The ending-color.
     * @param chameleon The color-changing-type. Check {@link Chameleon}
     */
    SingleColorPageAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, Integer fromColor, Integer toColor, Chameleon chameleon) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack);
        this.fromColor = fromColor;
        this.toColor = toColor;
        this.chameleon = chameleon;

        prepareColor();
    }

    private void prepareColor() {
        if (chameleon == Chameleon.RGB) {
            fromARGBArray = Chameleon.toARGBArray(fromColor);
            toARGBArray = Chameleon.toARGBArray(toColor);
        } else if (chameleon == Chameleon.HSV) {
            fromHSVArray = Chameleon.toHSVArray(fromColor);
            toHSVArray = Chameleon.toHSVArray(toColor);
        }
    }

    int middleColor(Chameleon chameleon, float offset) {
        if (chameleon == Chameleon.RGB) return middleRGBColor(offset);
        else if (chameleon == Chameleon.HSV) return middleHSVColor(offset);
        throw new RuntimeException("Unknown Chameleon!");
    }

    private int middleRGBColor(float offset) {
        return Chameleon.getARGBColor(fromARGBArray, toARGBArray, offset);
    }

    private int middleHSVColor(float offset) {
        return Chameleon.getHSVColor(fromHSVArray, toHSVArray, offset);
    }

    static class Builder extends PageAnimation.Builder {

        Integer fromColor = null;
        Integer toColor = null;
        Chameleon chameleon = Chameleon.RGB;

        @Override
        protected void checkUninitializedAttributes() {
            if (fromColor == null) uninitializedAttributeException("fromColor");
            if (toColor == null) uninitializedAttributeException("toColor");
        }
    }

}
