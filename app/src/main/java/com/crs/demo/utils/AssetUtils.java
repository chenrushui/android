package com.crs.demo.utils;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created on 2016/9/13.
 * Author:crs
 * Description:资产文件工具类
 */
public class AssetUtils {

    /**
     * 获取字符串
     *
     * @param fileName
     * @param assetManager
     * @return
     */
    public static String getStrFromAsset(String fileName, AssetManager assetManager) {
        InputStream fis = null;
        if (assetManager == null) {
            return "";
        }

        try {
            fis = assetManager.open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String str;
            //每次读取一行，说明读取到内容
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //无论如何都会执行的方法
            //文件读写和json解析一般都会出现异常的  IOException/JSONException
            //文件读写最消耗手机性能、特别是下载（手机长时间放电，导致手机发热）
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        return "";
    }

}
