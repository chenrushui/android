package com.crs.demo.ui.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

/**
 * Created on 2016/8/23.
 * Author:crs
 * Description:罗桂林的效果
 */
public class LuoActivity extends BaseActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private TextView tv_bottom;
    private TextView tv_content;
    private int parentWidth;
    FrameLayout rl;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luo);

        initViews();
        initListener();
    }

    private void initListener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    private void initViews() {
        tv_bottom = findView(R.id.tv_bottom);
        tv_content = findView(R.id.tv_content);
        tv_text = findView(R.id.tv_text);

        btn1 = findView(R.id.btn1);
        btn2 = findView(R.id.btn2);
        rl = findView(R.id.rl);


        //获取底层控件的宽度
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) tv_bottom.getLayoutParams();
        parentWidth = params.width;


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1: {
                //设置布局参数
                FrameLayout.LayoutParams paramsContent = (FrameLayout.LayoutParams) tv_content.getLayoutParams();
                paramsContent.height = FrameLayout.LayoutParams.MATCH_PARENT;
                paramsContent.width = parentWidth / 6 * 4;
                //设置背景颜色
                tv_content.setBackgroundColor(Color.parseColor("#70b9eb"));
                //设置文字
                tv_text.setText("4/6");
                //设置边框背景
                rl.setBackgroundResource(R.drawable.shape_luo_frame_layout);
            }
            break;
            case R.id.btn2: {
            }
            break;
        }

    }
}
