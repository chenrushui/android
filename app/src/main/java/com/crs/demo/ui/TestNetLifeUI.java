package com.crs.demo.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.utils.HttpUtils;
import com.squareup.okhttp.OkHttpClient;

import java.util.HashMap;

/**
 * Created on 2016/8/19.
 * Author:crs
 * Description:测试网络框架的生命周期方法
 */
public class TestNetLifeUI extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_net_life);

        HttpUtils httpUtils = new HttpUtils();
        HashMap<String, String> params = new HashMap<>();
    }

}
