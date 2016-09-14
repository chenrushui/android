package com.crs.demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created on 2016/9/9.
 * Author:crs
 * Description:网络连接工具类
 */
public class ConnUtils {
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                boolean connected = activeNetworkInfo.isConnected();
                return connected;
            }
        }
        return false;
    }
}
