package com.nightonke.wowoviewpager.Animation;

import android.view.View;

import com.nightonke.wowoviewpager.Enum.Ease;

/**
 * Created by Weiping Huang at 19:51 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class WoWoInterfaceAnimation extends PageAnimation {

    private WoWoAnimationInterface animationInterface;

    public WoWoInterfaceAnimation(int page, float startOffset, float endOffset, Ease ease, boolean useSameEaseEnumBack, WoWoAnimationInterface animationInterface) {
        super(page, startOffset, endOffset, ease, useSameEaseEnumBack);
        this.animationInterface = animationInterface;
    }

    public WoWoAnimationInterface getAnimationInterface() {
        return animationInterface;
    }

    public void setAnimationInterface(WoWoAnimationInterface animationInterface) {
        this.animationInterface = animationInterface;
    }

    @Override
    protected void toStartState(View view) {
        if (animationInterface != null) animationInterface.toStartState();
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        if (animationInterface != null) animationInterface.toMiddleState(offset);
    }

    @Override
    protected void toEndState(View view) {
        if (animationInterface != null) animationInterface.toEndState();
    }
}
