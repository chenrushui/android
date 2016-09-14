package com.crs.demo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:测试自己封装的网络请求框架
 */
public class TestNetWorkActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_net_work);

        initViews();
    }

    private void initViews() {
        Button btn_get = findView(R.id.btn_get);
        Button btn_post = findView(R.id.btn_post);
        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get: {

            }
            break;
            case R.id.btn_post: {

            }
            break;

        }

    }
}

