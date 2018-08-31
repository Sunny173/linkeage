package com.oo2oo.linkage;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by sun on 2018/8/3.
 */

public class BgUtil {
    /**
     * 产生shape类型的drawable
     * <p>
     * //     * @param solidColor
     *
     * @param strokeColor
     * @param strokeWidth
     * @param radius
     * @return
     */
    public static GradientDrawable getBackgroundDrawable(int strokeColor, int strokeWidth, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadius(radius);
        return drawable;
    }
}
