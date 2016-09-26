package com.crs.demo.ui.handler;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

/**
 * Created on 2016/9/23.
 * Author:crs
 * Description:测试消息机制
 */
public class TestHandlerActivity extends BaseActivity implements View.OnClickListener {

    private Handler mHandler = new Handler();
    private TextView tv_test_handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);

        Button btn_handler = findView(R.id.btn_test_handler);
        btn_handler.setOnClickListener(this);

        tv_test_handler = findView(R.id.tv_test_handler);

    }

    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_handler: {
                //创建一个子线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            //run方法中是在主线程执行的
                            @Override
                            public void run() {

                                tv_test_handler.setText("更新UI的操作");
                            }
                        });
                    }
                }).start();

            }
            break;
        }
    }

    /**
     * handler.post(r): r是要执行的任务代码,意思就是说r的代码实际是在UI线程执行的,可以写更新UI的代码。
     * Runnable是继承Thread的一个接口。
     */
}
