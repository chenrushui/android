package com.crs.demo.ui.handler;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

/**
 * Created on 2016/10/8.
 * Author:crs
 * Description:使用消息机制实现图片轮播效果
 * 关键是练习消息机制的使用
 */
public class TestPostDelayedActivity extends BaseActivity {

    int[] image = {R.drawable.iv_1, R.drawable.iv_2, R.drawable.iv_3};
    int index;
    private ImageView iv_test_post_delayed;

    //1)创建Handler实例
    private Handler mHandler = new Handler();
    //2)创建Runnable实例
    private MyRunnable myRunnable = new MyRunnable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_post_delayed);
        iv_test_post_delayed = findView(R.id.iv_test_post_delayed);

        //3)调用此方法实现定时器效果
        mHandler.postDelayed(myRunnable, 1000);
    }

    //实现接口
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            index++;
            //余数只有三种类型0、1、2
            index = index % 3;
            //每隔多少秒要做的事(业务逻辑)
            iv_test_post_delayed.setImageResource(image[index]);
            mHandler.postDelayed(this, 1000);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //清除此定时器效果
        mHandler.removeCallbacks(myRunnable);
    }
}
