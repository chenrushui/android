package com.crs.demo.base;

import android.app.Application;

import com.crs.demo.utils.logger.LogLevel;
import com.crs.demo.utils.logger.Logger;

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

        //进行初始化操作
        Logger.init("TUHU")
                .setMethodCount(5)            // default 2
                .hideThreadInfo()             // default it is shown
                .setLogLevel(LogLevel.FULL);  //NONE
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
