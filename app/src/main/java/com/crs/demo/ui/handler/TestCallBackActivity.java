package com.crs.demo.ui.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.utils.ToastUtils;

/**
 * Created on 2016/10/8.
 * Author:crs
 * Description:测试创建Handler指定CallBack
 */
public class TestCallBackActivity extends BaseActivity {

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //可以通过CallBack截获Handler传递的消息,返回值表示是否截获消息，false表示不截获。
            ToastUtils.showShort(TestCallBackActivity.this, "1");
            //如果设置为true，就表示截获消息，后面的handleMessage()就不在继续执行了，即2不会被打印了。
            return false;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            ToastUtils.showShort(TestCallBackActivity.this, "2");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_call_back);

        initViews();

    }

    private void initViews() {
        Button btn_activity_test_call_back = findView(R.id.btn_activity_test_call_back);
        btn_activity_test_call_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessage(1);
            }
        });
    }
}
