package com.crs.demo.ui.handler;

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
 * Description:消息机制的使用,主要用于线程间通讯。
 * 1)一个完整的消息要包括消息标识和消息内容.
 * 2)handler.post(r): r是要执行的任务代码,意思就是说r的代码实际是在UI线程执行的,可以写更新UI的代码。
 */
public class HandleActivity extends BaseActivity implements View.OnClickListener {
    private static final int SUCCESS_GET_DATE = 0;
    private Button btn_handler;

    //回调handleMessage()方法
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //接收子线程发送的消息
                case SUCCESS_GET_DATE: {
                    StudentEntity student = (StudentEntity) msg.obj;
                    String name = student.getName();
                    int age = student.getAge();
                    ToastUtils.showLong(HandleActivity.this, name + age);
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
        btn_handler.setOnClickListener(this);
    }

    private void initViews() {
        btn_handler = findView(R.id.btn_handler);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn: {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String json = "{name:陈如水,age:20}";
                        processJson(json);
                    }
                }).start();

            }
            break;
        }
    }

    private void processJson(String json) {
        Gson gson = new Gson();
        //按照json结点解析获取所有的键对应的值，然后赋值给对象对应的键
        StudentEntity student = gson.fromJson(json, StudentEntity.class);
        //Message message = mHandle.obtainMessage(SUCCESS_GET_DATE, student);
        //一个完整的消息要包括消息标识和消息内容
        Message message = new Message();
        message.obj = student;
        message.what = SUCCESS_GET_DATE;

        //Handler传递消息的两种方式:内部的实现原理都是一样的
        //message.sendToTarget();
        mHandle.sendMessage(message);
    }
}
