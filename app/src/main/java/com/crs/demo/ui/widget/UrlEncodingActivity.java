package com.crs.demo.ui.widget;

import android.os.Bundle;

import com.crs.demo.base.BaseActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created on 2016/10/8.
 * Author:crs
 * Description:测试url编解码类
 */
public class UrlEncodingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //使用前提:只有汉语才存在编解码问题，关键是URLEncoder encode() decode()

        try {
            //把汉语进行编码
            String content = URLEncoder.encode("中国", "UTF-8");
            //进行解码
            String encode = URLEncoder.encode(content, "UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
