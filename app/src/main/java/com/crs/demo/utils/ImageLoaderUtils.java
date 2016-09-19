package com.crs.demo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created on 2016/9/18.
 * Author:crs
 * Description:自己再封装一层图片加载框架 特别有用，一定要注意
 */
public class ImageLoaderUtils {

    /**
     * 这样当改变图片加载框架时，只需要修改此类即可，不用每一个用到的地方都修改
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void loadImage(Context context, String url, ImageView iv) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).into(iv);
        } else {
            //指定默认的图片路径
        }
    }

}
