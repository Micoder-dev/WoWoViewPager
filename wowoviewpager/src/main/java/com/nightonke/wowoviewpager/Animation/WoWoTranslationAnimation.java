package com.nightonke.wowoviewpager.Animation;

import android.view.View;

import com.nightonke.wowoviewpager.Enum.Ease;

/**
 * Created by Weiping Huang at 20:12 on 2017/3/29
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change 2D-translation of a view.
 */

public class WoWoTranslationAnimation extends PageAnimation {

    private float fromX = UNINITIALIZED_VALUE;
    private float fromY = UNINITIALIZED_VALUE;
    private float toX = UNINITIALIZED_VALUE;
    private float toY = UNINITIALIZED_VALUE;

    public WoWoTranslationAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, float fromX, float fromY, float toX, float toY) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack);
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    protected void toStartState(View view) {
        view.setTranslationX(fromX);
        view.setTranslationY(fromY);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        view.setTranslationX(fromX + (toX - fromX) * offset);
        view.setTranslationY(fromY + (toY - fromY) * offset);
    }

    @Override
    protected void toEndState(View view) {
        view.setTranslationX(toX);
        view.setTranslationY(toY);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PageAnimation.Builder {

        private float fromX = UNINITIALIZED_VALUE;
        private float fromY = UNINITIALIZED_VALUE;
        private float toX = UNINITIALIZED_VALUE;
        private float toY = UNINITIALIZED_VALUE;

        public Builder page(int page) { this.page = page; return this; }

        public Builder start(float startOffset) { this.startOffset = startOffset; return this; }

        public Builder start(double startOffset) { return start((float) startOffset); }

        public Builder end(float endOffset) { this.endOffset = endOffset; return this; }

        public Builder end(double endOffset) { return end((float) endOffset); }

        public Builder ease(Ease ease) { this.ease = ease; return this; }

        public Builder sameEaseBack(boolean useSameEaseEnumBack) { this.useSameEaseEnumBack = useSameEaseEnumBack; return this; }

        public Builder fromX(float fromX) { this.fromX = fromX; return this; }

        public Builder fromX(double fromX) { return fromX((float) fromX); }

        public Builder toX(float toX) { this.toX = toX; return this; }

        public Builder toX(double toX) { return toX((float) toX); }

        public Builder keepX(float x) { return fromX(x).toX(x); }

        public Builder keepX(double x) { return keepX((float) x); }

        public Builder fromY(float fromY) { this.fromY = fromY; return this; }

        public Builder fromY(double fromY) { return fromY((float) fromY); }

        public Builder toY(float toY) { this.toY = toY; return this; }

        public Builder toY(double toY) { return toY((float) toY); }

        public Builder keepY(float y) { return fromY(y).toY(y); }

        public Builder keepY(double y) { return keepY((float) y); }

        public WoWoTranslationAnimation build() {
            checkUninitializedAttributes();
            return new WoWoTranslationAnimation(page, startOffset, endOffset, ease, useSameEaseEnumBack, fromX, fromY, toX, toY);
        }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromX == UNINITIALIZED_VALUE) uninitializedAttributeException("fromX");
            if (fromY == UNINITIALIZED_VALUE) uninitializedAttributeException("fromY");
            if (toX == UNINITIALIZED_VALUE) uninitializedAttributeException("toX");
            if (toY == UNINITIALIZED_VALUE) uninitializedAttributeException("toY");
        }
    }
}
