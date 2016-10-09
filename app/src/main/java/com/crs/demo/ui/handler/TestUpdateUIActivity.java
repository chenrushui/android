package com.crs.demo.ui.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

/**
 * Created on 2016/10/9.
 * Author:crs
 * Description:安卓中四种更新UI的方式
 * 1)handler.post()
 * 2)handler.sendMessage()
 * 3)runOnUIThread()
 * 4)View.post
 */
public class TestUpdateUIActivity extends BaseActivity {

    private static final int SUCCESS_GET_DATA = 1;
    private Handler mHandler = new Handler();
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS_GET_DATA: {
                    tv_activity_test_update_ui.setText("使用handler.sendMessage()更新");
                }
                break;
            }
        }
    };
    private TextView tv_activity_test_update_ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_update_ui);

        initViews();
    }

    private void initViews() {
        tv_activity_test_update_ui = findView(R.id.tv_activity_test_update_ui);
        Button btn_test_handler1 = findView(R.id.btn_test_handler1);
        Button btn_test_handler2 = findView(R.id.btn_test_handler2);
        Button btn_test_handler3 = findView(R.id.btn_test_handler3);
        Button btn_test_handler4 = findView(R.id.btn_test_handler4);

        //第一种方式:在子线程中调用runOnUIThread()更细UI
        btn_test_handler1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_activity_test_update_ui.setText("调用runOnUIThread方法更新");
                            }
                        });
                    }
                }).start();

            }
        });

        //第二种方式:使用View的post()更新UI
        btn_test_handler2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_activity_test_update_ui.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_activity_test_update_ui.setText("使用View的post()更新UI");
                    }
                });

            }
        });

        //第三种方式:使用Handler的post()更新UI
        btn_test_handler3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_activity_test_update_ui.setText("Handler的post()更新UI");
                            }
                        });
                    }
                }).start();
            }
        });

        //第四种方式:使用Handler的sendMessage()更新UI
        btn_test_handler4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Message message = new Message();
                        //Message message = myHandler.obtainMessage();
                        myHandler.sendEmptyMessage(SUCCESS_GET_DATA);
                    }
                }).start();
            }
        });
    }
}
