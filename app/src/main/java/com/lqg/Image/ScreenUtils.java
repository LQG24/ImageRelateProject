package com.lqg.Image;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;


/**
 * Created by Administrator on 2019/5/15.
 */

public class ScreenUtils {

    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //noinspection ConstantConditions
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            //noinspection ConstantConditions
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /**
     * Return the height of screen, in pixel.
     *
     * @return the height of screen, in pixel
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //noinspection ConstantConditions
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            //noinspection ConstantConditions
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    public static TextView createFlexItemView(Context context, String content, boolean selected, boolean addItem) {
        TextView textView = new TextView(context);
        textView.setTextSize(12);
        textView.setPadding(30, 10, 30, 10);
        if (selected) {
            textView.setText(content + " ×");
            textView.setTextColor(context.getResources().getColor(R.color.btn_text_color));
            textView.setBackgroundResource(R.drawable.corner_13_bg);
        } else {
            textView.setText(content);
            textView.setTextColor(context.getResources().getColor(R.color.normal_text_color));
            textView.setBackgroundResource(R.drawable.corner_13_light_black_bg);
        }

        if (addItem) {
            textView.setText("+ " + content);
            textView.setTextColor(context.getResources().getColor(R.color.btn_presss_text));
            textView.setBackgroundResource(R.drawable.corner_13_stroke_bg);
        }
        textView.setClickable(true);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }


    public static ViewGroup.LayoutParams createDefaultLayoutParams() {
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //它假设每个子项的 layout_flexGrow 属性的值设为 1，那么剩余空间将均匀分配到每个子项。默认为0
        lp.setMargins(0, SizeUtils.dp2px(8), SizeUtils.dp2px(8), 0);
        return lp;
    }

}
