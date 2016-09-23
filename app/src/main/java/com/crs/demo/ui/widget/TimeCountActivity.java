package com.crs.demo.ui.widget;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

/**
 * Created on 2016/8/24.
 * Author:crs
 * Description:定时器常用类
 */
public class TimeCountActivity extends BaseActivity {

    private Button btn;
    private TimeCount timeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_count);

        initViews();

    }

    private void initViews() {
        btn = findView(R.id.btn);
        timeCount = new TimeCount(60000, 1000);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeCount.start();
            }
        });
    }

    //1，实现父类中的抽象方法   抽象类
    //2，添加构造方法
    //3，具体的操作都在子类中实现了
    private class TimeCount extends CountDownTimer {
        //添加构造方法
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //每秒钟都会被调用一次
        //在倒计时的过程中  按钮不能点击
        //按钮上显示的文字：秒数的改变
        @Override
        public void onTick(long l) {
            btn.setClickable(false);
            btn.setText(l / 1000 + "后重新发送");

        }

        //当倒计时走完的时候，调用此方法
        @Override
        public void onFinish() {
            btn.setClickable(true);
            btn.setText("点击获取验证码");

        }
    }

}

