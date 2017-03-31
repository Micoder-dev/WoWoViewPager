package com.nightonke.wowoviewpager.Animation;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.View;

import com.nightonke.wowoviewpager.Enum.Chameleon;
import com.nightonke.wowoviewpager.Enum.Ease;

import java.util.List;

import static com.nightonke.wowoviewpager.WoWoViewPager.TAG;

/**
 * Created by Weiping Huang at 12:25 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change multi-colors for state-list {@link StateListDrawable}
 */

public class WoWoStateListColorAnimation extends MultiColorPageAnimation {

    /**
     * Construct a page animation for multi-colors translation.
     *
     * @param page                The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset         The animation only plays when the offset of page is large than startOffset.
     * @param endOffset           The animation only plays when the offset of page is less than endOffset.
     * @param ease            The ease type of the animation.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromColors          The starting-colors.
     * @param toColors            The ending-colors.
     * @param chameleon           The color-changing-type. Check {@link Chameleon}
     */
    public WoWoStateListColorAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, int[] fromColors, int[] toColors, Chameleon chameleon) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack, fromColors, toColors, chameleon);
    }

    @Override
    protected void toStartState(View view) {
        setColors(view, fromColors);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        setColors(view, middleColors(chameleon, offset));
    }

    @Override
    protected void toEndState(View view) {
        setColors(view, toColors);
    }

    private void setColors(View view, int[] colors) {
        Drawable drawable = view.getBackground();
        if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            DrawableContainerState drawableContainerState = (DrawableContainerState) stateListDrawable.getConstantState();
            if (drawableContainerState != null) {
                Drawable[] drawables = drawableContainerState.getChildren();
                for (int i = 0; i < colors.length; i++) if (drawables[i] instanceof GradientDrawable) ((GradientDrawable) drawables[i]).setColor(colors[i]);
            }
        } else Log.w(TAG, "Drawable of view must be StateListDrawable in WoWoStateListColorAnimation");
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends MultiColorPageAnimation.Builder {

        public Builder page(int page) { this.page = page; return this; }

        public Builder start(float startOffset) { this.startOffset = startOffset; return this; }

        public Builder start(double startOffset) { return start((float) startOffset); }

        public Builder end(float endOffset) { this.endOffset = endOffset; return this; }

        public Builder end(double endOffset) { return end((float) endOffset); }

        public Builder ease(Ease ease) { this.ease = ease; return this; }

        public Builder sameEaseBack(boolean useSameEaseEnumBack) { this.useSameEaseEnumBack = useSameEaseEnumBack; return this; }

        public Builder from(int... fromColors) { this.fromColors = fromColors; return this; }

        public Builder from(String... fromColors) {
            this.fromColors = new int[fromColors.length];
            for (int i = 0; i < fromColors.length; i++) this.fromColors[i] = Color.parseColor(fromColors[i]);
            return this;
        }

        public Builder from(List<Object> fromColors) {
            this.fromColors = new int[fromColors.size()];
            for (int i = 0; i < fromColors.size(); i++) {
                Object color = fromColors.get(i);
                if (color instanceof Integer) this.fromColors[i] = (int) color;
                else if (color instanceof String) this.fromColors[i] = Color.parseColor((String) color);
            }
            return this;
        }

        public Builder to(int... toColors) { this.toColors = toColors; return this; }

        public Builder to(String... toColors) {
            this.toColors = new int[toColors.length];
            for (int i = 0; i < toColors.length; i++) this.toColors[i] = Color.parseColor(toColors[i]);
            return this;
        }

        public Builder to(List<Object> toColors) {
            this.toColors = new int[toColors.size()];
            for (int i = 0; i < toColors.size(); i++) {
                Object color = toColors.get(i);
                if (color instanceof Integer) this.toColors[i] = (int) color;
                else if (color instanceof String) this.toColors[i] = Color.parseColor((String) color);
            }
            return this;
        }

        public Builder chameleon(Chameleon chameleon) { this.chameleon = chameleon; return this; }

        public WoWoStateListColorAnimation build() {
            checkUninitializedAttributes();
            return new WoWoStateListColorAnimation(page, startOffset, endOffset, ease, useSameEaseEnumBack, fromColors, toColors, chameleon);
        }
    }
}
