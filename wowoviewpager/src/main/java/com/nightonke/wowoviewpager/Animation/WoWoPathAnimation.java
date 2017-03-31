package com.nightonke.wowoviewpager.Animation;

import com.nightonke.wowoviewpager.Enum.Ease;
import com.nightonke.wowoviewpager.WoWoPathView;

/**
 * Created by Weiping Huang at 19:56 on 2016/3/3
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to perform a moving path with a image-head.
 */

public class WoWoPathAnimation extends WoWoInterfaceAnimation {

    public WoWoPathAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, WoWoAnimationInterface animationInterface) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack, animationInterface);
    }

    public void setPathView(WoWoPathView pathView) {
        setAnimationInterface(pathView);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PageAnimation.Builder {

        private WoWoPathView pathView = null;

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder start(float startOffset) {
            this.startOffset = startOffset;
            return this;
        }

        public Builder start(double startOffset) {
            this.startOffset = (float) startOffset;
            return this;
        }

        public Builder end(float endOffset) {
            this.endOffset = endOffset;
            return this;
        }

        public Builder end(double endOffset) {
            this.endOffset = (float) endOffset;
            return this;
        }

        public Builder ease(Ease ease) {
            this.ease = ease;
            return this;
        }

        public Builder sameEaseBack(boolean useSameEaseEnumBack) {
            this.useSameEaseEnumBack = useSameEaseEnumBack;
            return this;
        }

        public Builder path(WoWoPathView pathView) {
            this.pathView = pathView;
            return this;
        }

        public WoWoPathAnimation build() {
            checkUninitializedAttributes();
            return new WoWoPathAnimation(page, startOffset, endOffset, ease, useSameEaseEnumBack, pathView);
        }

        @Override
        protected void checkUninitializedAttributes() {
            if (pathView == null) uninitializedAttributeException("pathView");
        }
    }
}
