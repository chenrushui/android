package com.crs.demo.ui.reflect;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

/**
 * Created on 2016/9/21.
 * Author:crs
 * Description:测试泛型的使用
 */
public class TestInflectUI extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_reflcet);

        initViews();
    }

    private void initViews() {
        TextView tv_test_reflect = findView(R.id.tv_test_reflect);
        tv_test_reflect.setText("测试泛型");
    }

}
