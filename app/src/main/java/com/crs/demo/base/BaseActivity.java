package com.crs.demo.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.crs.demo.R;
import com.crs.demo.utils.StatusBarUtils;

/**
 * Created on 2016/8/19.
 * Author:crs
 * Description:BaseActivity的抽取
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置状态栏的颜色
        setStatusBar();
    }


    //如果想限定一个变量的类型，必须写在前面进行泛型限定，格式：<T extends Objects> T
    protected <T extends View> T findView(int id) {
        //这是强制类型转化,这样写的好处是每次实例化控件，不用在强制类型转化
        return (T) findViewById(id);
    }


    /**
     * 设置状态栏的颜色
     */
    private void setStatusBar() {
        StatusBarUtils.setColor(this, getResources().getColor(R.color.color_status_bar));
    }
}
