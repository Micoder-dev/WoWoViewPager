package com.nightonke.wowoviewpager.Animation;

import android.view.View;

import com.nightonke.wowoviewpager.Enum.Ease;

/**
 * Created by Weiping Huang at 00:20 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change rotate degree of view.
 *          _ _ _ _ _ _ _
 *        /|    x
 *       / |
 *      /  |y
 *     /   |
 *    /z   |
 *   /     |
 *
 */

public class WoWoRotationAnimation extends PageAnimation {

    private float fromX = UNINITIALIZED_VALUE;
    private float fromY = UNINITIALIZED_VALUE;
    private float fromZ = UNINITIALIZED_VALUE;
    private float toX = UNINITIALIZED_VALUE;
    private float toY = UNINITIALIZED_VALUE;
    private float toZ = UNINITIALIZED_VALUE;

    public WoWoRotationAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, float fromX, float fromY, float fromZ, float toX, float toY, float toZ) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack);
        this.fromX = fromX;
        this.fromY = fromY;
        this.fromZ = fromZ;
        this.toX = toX;
        this.toY = toY;
        this.toZ = toZ;
    }

    @Override
    protected void toStartState(View view) {
        view.setRotationX(fromX);
        view.setRotationY(fromY);
        view.setRotation(fromZ);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        view.setRotationX(fromX + (toX - fromX) * offset);
        view.setRotationY(fromY + (toY - fromY) * offset);
        view.setRotation(fromZ + (toZ - fromZ) * offset);
    }

    @Override
    protected void toEndState(View view) {
        view.setRotationX(toX);
        view.setRotationY(toY);
        view.setRotation(toZ);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PageAnimation.Builder<WoWoRotationAnimation.Builder> {

        private float fromX = UNINITIALIZED_VALUE;
        private float fromY = UNINITIALIZED_VALUE;
        private float fromZ = UNINITIALIZED_VALUE;
        private float toX = UNINITIALIZED_VALUE;
        private float toY = UNINITIALIZED_VALUE;
        private float toZ = UNINITIALIZED_VALUE;

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

        public Builder fromZ(float fromZ) { this.fromZ = fromZ; return this; }

        public Builder fromZ(double fromZ) { return fromZ((float) fromZ); }

        public Builder toZ(float toZ) { this.toZ = toZ; return this; }

        public Builder toZ(double toZ) { return toZ((float) toZ); }

        public Builder keepZ(float z) { return fromZ(z).toZ(z); }

        public Builder keepZ(double z) { return keepZ((float) z); }

        public WoWoRotationAnimation build() {
            checkUninitializedAttributes();
            return new WoWoRotationAnimation(page, startOffset, endOffset, ease, useSameEaseEnumBack, fromX, fromY, fromZ, toX, toY, toZ);
        }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromX == UNINITIALIZED_VALUE) uninitializedAttributeException("fromX");
            if (fromY == UNINITIALIZED_VALUE) uninitializedAttributeException("fromY");
            if (fromZ == UNINITIALIZED_VALUE) uninitializedAttributeException("fromZ");
            if (toX == UNINITIALIZED_VALUE) uninitializedAttributeException("toX");
            if (toY == UNINITIALIZED_VALUE) uninitializedAttributeException("toY");
            if (toZ == UNINITIALIZED_VALUE) uninitializedAttributeException("toZ");
        }
    }
}
