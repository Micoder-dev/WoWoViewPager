package com.nightonke.wowoviewpager.Animation;

import com.nightonke.wowoviewpager.Enum.Chameleon;
import com.nightonke.wowoviewpager.Enum.Ease;

/**
 * Created by Weiping Huang at 12:07 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

abstract class MultiColorPageAnimation extends PageAnimation {

    int[] fromColors = null;
    int[] toColors = null;
    Chameleon chameleon = Chameleon.RGB;

    private int[][] fromARGBArrays = null;
    private int[][] toARGBArrays = null;
    private float[][] fromHSVArrays = null;
    private float[][] toHSVArrays = null;

    /**
     * Construct a page animation for multi-colors translation.
     *
     * @param page The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset The animation only plays when the offset of page is large than startOffset.
     * @param endOffset The animation only plays when the offset of page is less than endOffset.
     * @param ease The ease type of the animation.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromColors The starting-colors.
     * @param toColors The ending-colors.
     * @param chameleon The color-changing-type. Check {@link Chameleon}
     */
    MultiColorPageAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, int[] fromColors, int[] toColors, Chameleon chameleon) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack);
        this.fromColors = fromColors;
        this.toColors = toColors;
        this.chameleon = chameleon;

        prepareColors();
    }

    int[] middleColors(Chameleon chameleon, float offset) {
        if (chameleon == Chameleon.RGB) return middleRGBColors(offset);
        else if (chameleon == Chameleon.HSV) return middleHSVColors(offset);
        throw new RuntimeException("Unknown Chameleon!");
    }

    private int[] middleRGBColors(float offset) {
        return Chameleon.getARGBColors(fromARGBArrays, toARGBArrays, offset);
    }

    private int[] middleHSVColors(float offset) {
        return Chameleon.getHSVColors(fromHSVArrays, toHSVArrays, offset);
    }

    private void prepareColors() {
        if (chameleon == Chameleon.RGB) {
            fromARGBArrays = Chameleon.toARGBArrays(fromColors);
            toARGBArrays = Chameleon.toARGBArrays(toColors);
        } else if (chameleon == Chameleon.HSV) {
            fromHSVArrays = Chameleon.toHSVArrays(fromColors);
            toHSVArrays = Chameleon.toHSVArrays(toColors);
        }
    }

    static class Builder extends PageAnimation.Builder {

        int[] fromColors = null;
        int[] toColors = null;
        Chameleon chameleon = Chameleon.RGB;

        @Override
        protected void checkUninitializedAttributes() {
            if (fromColors == null) uninitializedAttributeException("fromColors");
            if (toColors == null) uninitializedAttributeException("toColors");
        }
    }
}
