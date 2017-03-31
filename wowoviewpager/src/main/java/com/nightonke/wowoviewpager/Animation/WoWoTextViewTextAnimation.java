package com.nightonke.wowoviewpager.Animation;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nightonke.wowoviewpager.Enum.Ease;
import com.nightonke.wowoviewpager.Enum.Typewriter;

import static com.nightonke.wowoviewpager.WoWoViewPager.TAG;

/**
 * Created by Weiping Huang at 14:14 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class WoWoTextViewTextAnimation extends PageAnimation {

    private String fromText = null;
    private String toText = null;
    private Typewriter typewriter = Typewriter.DeleteThenType;

    public WoWoTextViewTextAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, String fromText, String toText, Typewriter typewriter) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack);
        this.fromText = fromText;
        this.toText = toText;
        this.typewriter = typewriter;
    }

    @Override
    protected void toStartState(View view) {
        setText(view, fromText);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        setText(view, Typewriter.nextString(fromText, toText, offset, typewriter));
    }

    @Override
    protected void toEndState(View view) {
        setText(view, toText);
    }

    private void setText(View view, String text) {
        if (view instanceof TextView) ((TextView) view).setText(text);
        else try {
            TextView.class.cast(view).setText(text);
        } catch (ClassCastException c) {
            Log.w(TAG, "View must be an instance of TextView in WoWoTextViewTextAnimation");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PageAnimation.Builder {

        private String fromText = null;
        private String toText = null;
        private Typewriter typewriter = Typewriter.DeleteThenType;

        public Builder page(int page) { this.page = page; return this; }

        public Builder start(float startOffset) { this.startOffset = startOffset; return this; }

        public Builder start(double startOffset) { return start((float) startOffset); }

        public Builder end(float endOffset) { this.endOffset = endOffset; return this; }

        public Builder end(double endOffset) { return end((float) endOffset); }

        public Builder ease(Ease ease) { this.ease = ease; return this; }

        public Builder sameEaseBack(boolean useSameEaseEnumBack) { this.useSameEaseEnumBack = useSameEaseEnumBack; return this; }

        public Builder from(String fromText) { this.fromText = fromText; return this; }

        public Builder to(String toText) { this.toText = toText; return this; }

        public Builder typewriter(Typewriter typewriter) { this.typewriter = typewriter; return this; }

        public WoWoTextViewTextAnimation build() {
            checkUninitializedAttributes();
            return new WoWoTextViewTextAnimation(page, startOffset, endOffset, ease, useSameEaseEnumBack, fromText, toText, typewriter);
        }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromText == null) uninitializedAttributeException("fromText");
            if (toText == null) uninitializedAttributeException("toText");
        }
    }
}
