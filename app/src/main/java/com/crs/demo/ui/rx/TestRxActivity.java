package com.crs.demo.ui.rx;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.constant.UrlConstant;

/**
 * Created on 2016/9/18.
 * Author:crs
 * Description:使用RxAndroid+OkHttp完成常规的网络操作
 */
public class TestRxActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_rx_login_name;
    private EditText et_rx_password;
    private Button btn_rx_login;
    private LoginUtils loginUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_okhttp);

        initViews();
        initListener();
        loginUtils = new LoginUtils();

    }


    private void initViews() {
        et_rx_login_name = findView(R.id.et_rx_login_name);
        et_rx_password = findView(R.id.et_rx_password);
        btn_rx_login = findView(R.id.btn_rx_login);
    }

    private void initListener() {
        btn_rx_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rx_login: {
                loginUtils.loginPost(UrlConstant.ORDER_STATUS_POST,
                        et_rx_login_name.getText().toString().trim(),
                        et_rx_password.getText().toString().trim());
            }
            break;

        }
    }
}

