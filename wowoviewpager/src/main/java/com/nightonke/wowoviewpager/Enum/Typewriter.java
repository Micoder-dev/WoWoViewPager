package com.nightonke.wowoviewpager.Enum;

/**
 * Created by Weiping Huang at 14:24 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public enum Typewriter {

    DeleteThenType;

    public static String nextString(String fromText, String toText, float offset, Typewriter typewriter) {
        if (offset < 0) offset = 0;
        if (offset > 1) offset = 1;
        switch (typewriter) {
            case DeleteThenType:
            {
                int sum = (fromText.length() + toText.length()) * 2;
                float current = sum * offset;
                if (current < 1) return fromText;
                else if (current < sum - toText.length() * 2 - 1) {
                    return fromText.substring(0, fromText.length() - (int) ((current + 1) / 2));
                } else if (current < fromText.length() * 2 + 1) {
                    return "";
                } else if (current < sum - 1) {
                    return toText.substring(0, (int) ((current - fromText.length() * 2 + 1) / 2));
                } else return toText;
            }
        }
        return nextString(fromText, toText, offset, DeleteThenType);
    }

}
