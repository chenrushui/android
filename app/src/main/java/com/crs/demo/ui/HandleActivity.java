package com.crs.demo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.bean.StudentEntity;
import com.crs.demo.utils.ToastUtils;
import com.google.gson.Gson;

/**
 * Created on 2016/8/24.
 * Author:crs
 * Description:消息机制的使用，线程间通讯
 */
public class HandleActivity extends BaseActivity implements View.OnClickListener {
    private static final int SUCCESS_GET_DATE = 0;
    private Button btn;
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS_GET_DATE: {
                    StudentEntity student = (StudentEntity) msg.obj;
                    String name = student.getName();
                    int age = student.getAge();
                    ToastUtils.showLong(HandleActivity.this,name + age);
                }
                break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);

        initViews();
        initListener();
    }

    private void initListener() {
        btn.setOnClickListener(this);
    }

    private void initViews() {
        btn = findView(R.id.btn);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn: {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String json = "{name:chenrushui,age:20}";
                        processJson(json);
                    }
                }).start();

            }
            break;
        }
    }

    private void processJson(String json) {
        Gson gson = new Gson();
        StudentEntity student = gson.fromJson(json, StudentEntity.class);
        Message message = mHandle.obtainMessage(SUCCESS_GET_DATE, student);
        message.sendToTarget();
    }
}
