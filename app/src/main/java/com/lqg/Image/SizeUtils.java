package com.lqg.Image;

import android.content.res.Resources;

/**
 * Created by Administrator on 2019/7/28.
 */

public class SizeUtils {
    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return value of px
     */
    public static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
