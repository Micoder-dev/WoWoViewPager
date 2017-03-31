package com.nightonke.wowoviewpager.Animation;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;

import com.nightonke.wowoviewpager.Enum.Chameleon;
import com.nightonke.wowoviewpager.Enum.Ease;

import static com.nightonke.wowoviewpager.WoWoViewPager.TAG;

/**
 * Created by Weiping Huang at 10:49 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change color for {@link GradientDrawable#setColor(int)}
 */

public class WoWoShapeColorAnimation extends SingleColorPageAnimation {

    public WoWoShapeColorAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, Integer fromColor, Integer toColor, Chameleon chameleon) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack, fromColor, toColor, chameleon);
    }

    @Override
    protected void toStartState(View view) {
        setColor(view, fromColor);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        setColor(view, middleColor(chameleon, offset));
    }

    @Override
    protected void toEndState(View view) {
        setColor(view, toColor);
    }

    private void setColor(View view, int color) {
        Drawable drawable = view.getBackground();
        if (drawable instanceof GradientDrawable) ((GradientDrawable) drawable).setColor(color);
        else Log.w(TAG, "Background must be an instance of GradientDrawable in WoWoShapeColorAnimation");
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends SingleColorPageAnimation.Builder {

        public Builder page(int page) { this.page = page; return this; }

        public Builder start(float startOffset) { this.startOffset = startOffset; return this; }

        public Builder start(double startOffset) { return start((float) startOffset); }

        public Builder end(float endOffset) { this.endOffset = endOffset; return this; }

        public Builder end(double endOffset) { return end((float) endOffset); }

        public Builder ease(Ease ease) { this.ease = ease; return this; }

        public Builder sameEaseBack(boolean useSameEaseEnumBack) { this.useSameEaseEnumBack = useSameEaseEnumBack; return this; }

        public Builder from(Integer fromColor) { this.fromColor = fromColor; return this; }

        public Builder from(String fromColor) { return from(Color.parseColor(fromColor)); }

        public Builder to(Integer toColor) { this.toColor = toColor; return this; }

        public Builder to(String toColor) { return to(Color.parseColor(toColor)); }

        public Builder chameleon(Chameleon chameleon) { this.chameleon = chameleon; return this; }

        public WoWoShapeColorAnimation build() {
            checkUninitializedAttributes();
            return new WoWoShapeColorAnimation(page, startOffset, endOffset, ease, useSameEaseEnumBack, fromColor, toColor, chameleon);
        }
    }
}
