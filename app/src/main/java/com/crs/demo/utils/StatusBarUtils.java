package com.crs.demo.utils;

import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

/**
 * Created on 2016/8/1.
 * Author:crs
 * Description:状态栏工具类
 */
public class StatusBarUtils {
    public static final int DEFAULT_STATUS_BAR_ALPHA = 0;

    public static void setColor(Activity activity, int color) {
        setColor(activity, color, DEFAULT_STATUS_BAR_ALPHA);
    }

    private static void setColor(Activity activity, int color, int defaultStatusBarAlpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setStatusBarColor(color);
        }
    }
}
