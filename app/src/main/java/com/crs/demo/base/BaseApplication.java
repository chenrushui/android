package com.crs.demo.base;

import android.app.Application;

/**
 * Created on 2016/8/24.
 * Author:crs
 * Description:BaseApplication
 */
public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static BaseApplication getInstance() {
        if (baseApplication == null) {
            baseApplication = new BaseApplication();
        }
        return baseApplication;
    }

    public static String getUUID() {
        return null;
    }

    public static String getChannelId() {
        return null;
    }

    //集成友盟推送 消息提醒
}
