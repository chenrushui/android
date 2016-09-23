package com.crs.demo.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.Button;

import com.crs.demo.R;

/**
 * Created on 2016/9/20.
 * Author:crs
 * Description:自定义按钮控件
 */
public class CustomButton extends Button {

    private Paint mPaint;
    private float mDensity;
    private int mCustomButtonColor;
    private float mCustomButtonAlpha;
    private boolean mHover;
    private float mMaxRadius;

    public CustomButton(Context context) {
        this(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化方法
        init();
        //设置按钮默认的颜色和透明度
        initCustomButtonColor(Color.BLACK, 0.2f);
        //初始化按钮显示样式
        initStyle(context, attrs);
        //重写父类中的方法:尺寸变化、触摸事件、绘制
    }


    private void init() {
        //获取资源对象，屏幕参数对象,屏幕密度
        Resources resources = getContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        mDensity = displayMetrics.density;

        //创建画笔，边缘抗锯齿(平滑),设置画笔的透明度0~255
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAlpha(100);
    }

    private void initCustomButtonColor(int black, float v) {
        mCustomButtonColor = black;
        mCustomButtonAlpha = v;
    }


    private void initStyle(Context context, AttributeSet attrs) {
        //获取属性类型数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
        mCustomButtonColor = typedArray.getColor(R.styleable.CustomButton_CustomColor, mCustomButtonColor);
        mCustomButtonAlpha = typedArray.getFloat(R.styleable.CustomButton_CustomAlpha, mCustomButtonAlpha);
        mHover = typedArray.getBoolean(R.styleable.CustomButton_CustomHover, mHover);
        typedArray.recycle();
    }

    private void setmHover(boolean isHover) {
        mHover = isHover;
    }

    //View的大小发生变化才会调用  TODO:重写父类中的方法 一定会调用吗？
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //设置按钮的弧度，设置平方根(20,10,16)
        mMaxRadius = (float) Math.sqrt(w * w + h * h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean superEvent = super.onTouchEvent(event);
        //if (event.getActionMasked())



        return superEvent;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
