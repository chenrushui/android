package com.crs.demo.ui.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.utils.LogUtils;

/**
 * Created on 2016/10/9.
 * Author:crs
 * Description:自定义与线程相关的Handler
 */
public class TestCustomHandlerActivity extends BaseActivity {

    private static final String TAG = "TestCustomHandler";
    private MyThread myThread;
    //创建主线程Handler
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LogUtils.i(TAG, Thread.currentThread() + "主线程");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_handler);



        myThread = new MyThread();
        myThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //主线程中的Handler
        mHandler.sendEmptyMessage(1);

        //子线程中的Handler
        myThread.myHandler.sendEmptyMessage(2);
    }


    class MyThread extends Thread {
        private Handler myHandler;

        @Override
        public void run() {
            super.run();
            Looper.prepare();
            myHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    LogUtils.i(TAG, Thread.currentThread() +"子线程");
                }
            };
            Looper.loop();
        }
    }
}
