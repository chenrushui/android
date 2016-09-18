package com.crs.demo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.bean.ResponseEntity;
import com.crs.demo.constant.UrlConstant;
import com.crs.demo.utils.LogUtils;
import com.crs.demo.utils.NetUtils;
import com.crs.demo.utils.ToastUtils;

import java.util.HashMap;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:测试自己封装的网络请求框架
 */
public class TestNetWorkActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TestNetWorkActivity";

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
                clickGet();
            }
            break;
            case R.id.btn_post: {
                clickPost();
            }
            break;

        }

    }

    private void clickPost() {
        NetUtils netUtils = new NetUtils(TestNetWorkActivity.this);
        netUtils.isFullUrl(true);
        netUtils.isShowDialog(true);
        netUtils.isPost(true);
        HashMap<String, String> params = new HashMap<>();
        params.put("orderNo", "TH01587458");
        netUtils.sendToApi(params, UrlConstant.ORDER_STATUS_POST, new NetUtils.ResponseCallBack() {
            @Override
            public void getResponseData(ResponseEntity response) {
                if (response.isSuccessful()) {
                    LogUtils.i(TAG, response.toString());
                    ToastUtils.showShort(TestNetWorkActivity.this, "网络请求成功！");
                }
            }
        });


    }

    private void clickGet() {
        NetUtils netUtils = new NetUtils(TestNetWorkActivity.this);
        netUtils.isFullUrl(true);
        netUtils.isPost(false);
        netUtils.isShowDialog(true);
        netUtils.sendToApi(null, UrlConstant.ORDER_STATUS, new NetUtils.ResponseCallBack() {
            @Override
            public void getResponseData(ResponseEntity response) {
                if (response.isSuccessful()) {
                    LogUtils.i(TAG, response.toString());
                    ToastUtils.showShort(TestNetWorkActivity.this, "网络请求成功！");
                }
            }
        });

    }
}

