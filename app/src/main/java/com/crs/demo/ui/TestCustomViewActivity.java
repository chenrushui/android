package com.crs.demo.ui;

import android.os.Bundle;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.view.CustomView;


/**
 * Created on 2016/8/25.
 * Author:crs
 * Description:测试自定义控件的api
 */
public class TestCustomViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        //显示出来看一下效果
        CustomView cv = findView(R.id.cv);

    }
}
