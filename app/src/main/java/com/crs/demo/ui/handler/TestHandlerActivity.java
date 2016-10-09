package com.crs.demo.ui.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

/**
 * Created on 2016/9/23.
 * Author:crs
 * Description:测试消息机制:主要用于线程间通讯，注意Handler类的构造函数
 * 1)一个完整的消息要包括消息标识和消息内容.(消息对象里面包含消息消息标识和消息内容;如果是空消息，只有消息标识没有消息体)
 *
 * 2)只有在原生线程(主线程)才能更新UI，在子线程更新UI会报错。
 *
 * 3)使用消息机制更新UI的模板性代码：创建Handler实例，在子线程中调用post方法,就两步操作。
 * handler.post(r): r是要执行的任务代码,意思就是说r的代码实际是在UI线程执行的,可以写更新UI的代码。
 *
 * 4)使用消息机制更新UI的模板性代码：创建Handler实例，重写handleMessage()方法，创建消息对象并进行信息传递即可。
 * obtainMessage() Message类 arg1 arg2 sendMessage(message实例) sendToTarget(); sendEmptyMessage()
 *
 * 5)使用消息机制实现图片轮播:创建Handler实例，创建Runnable接口实例，调用postDelayed(runnable实例，毫秒值);
 * removeCallbacks(Runnable),就这几步常规的操作
 *
 * 6)创建Handler的时候，指定callBack;可以通过CallBack截获Handler传递的消息.
 */
public class TestHandlerActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_test_handler;
    private Handler mHandler = new Handler(Looper.getMainLooper());

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
            case R.id.btn_test_handler: {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                //run方法里面的代码是在主线程里面执行的
                                tv_test_handler.setText("守望江湖!");
                            }
                        });
                    }
                }).start();

            }
            break;
        }
    }

}
