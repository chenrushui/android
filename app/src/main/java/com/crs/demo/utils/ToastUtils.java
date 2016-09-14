package com.crs.demo.utils;

import android.content.Context;
import android.widget.Toast;

import com.crs.demo.base.BaseApplication;

/**
 * Created on 2016/8/1.
 * Author:crs
 * Description:ToastUtils工具类
 */
public class ToastUtils {

    //但是这样写的弊端是showLong(String msg) 永远销毁不了，因为持有application的引用，变相消耗内存。
    public static void showLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
