package com.crs.demo.ui.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created on 2016/9/18.
 * Author:crs
 * Description:消息机制
 */
public class Handle1Activity extends BaseActivity {
    private static final int SUCCESS_LOAD_DATA = 0;
    private TextView tv_handle_1;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_1);

        Button btn_handle_1 = findView(R.id.btn_handle_1);
        tv_handle_1 = findView(R.id.tv_handle_1);

        //开启线程
        new Thread(new MyRunnable()).start();

        btn_handle_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.obj = "{name:chenrushui,age:20}";
                msg.what = SUCCESS_LOAD_DATA;
                mHandler.sendMessage(msg);
            }
        });
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {

            //循环消息队列
            Looper.prepare();

            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case SUCCESS_LOAD_DATA: {
                            JSONObject obj = (JSONObject) msg.obj;
                            try {
                                String name = obj.getString("name");
                                int age = obj.getInt("age");
                                tv_handle_1.setText(name + age);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                }
            };

            //直到循环消息队列结束
            Looper.loop();

        }
    }
}
