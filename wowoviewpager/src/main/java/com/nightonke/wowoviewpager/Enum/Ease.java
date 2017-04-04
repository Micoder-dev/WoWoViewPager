package com.nightonke.wowoviewpager.Enum;

/**
 * Created by Weiping Huang at 02:19 on 2017/3/29
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public enum Ease {

    InSine(0),
    OutSine(1),
    InOutSine(2),

    InQuad(3),
    OutQuad(4),
    InOutQuad(5),

    InCubic(6),
    OutCubic(7),
    InOutCubic(8),

    InQuart(9),
    OutQuart(10),
    InOutQuart(11),

    InQuint(12),
    OutQuint(13),
    InOutQuint(14),

    InExpo(15),
    OutExpo(16),
    InOutExpo(17),

    InCirc(18),
    OutCirc(19),
    InOutCirc(20),

    InBack(21),
    OutBack(22),
    InOutBack(23),

    InElastic(24),
    OutElastic(25),
    InOutElastic(26),

    InBounce(27),
    OutBounce(28),
    InOutBounce(29),

    Linear(30),
    Unknown(-1);

    private int value = 30;

    private float startX = 0, startY = 0, endX = 1, endY = 1;

    Ease(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Ease getEnum(int value) {
        if (value < 0 || value >= values().length) return Unknown;
        return values()[value];
    }

    public float getStartX() { return startX; }

    public Ease startX(float startX) { this.startX = startX; return this; }

    public float getStartY() { return startY; }

    public Ease startY(float startY) { this.startY = startY; return this; }

    public float getEndX() { return endX; }

    public Ease endX(float endX) { this.endX = endX; return this; }

    public float getEndY() { return endY; }

    public Ease endY(float endY) { this.endY = endY; return this; }

    public Ease startX(double startX) { return startX((float) startX); }

    public Ease startY(double startY) { return startY((float) startY); }

    public Ease endX(double endX) { return endX((float) endX); }

    public Ease endY(double endY) { return endY((float) endY); }
}
