package com.crs.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created on 2016/8/25.
 * Author:crs
 * Description:自定义view的api
 */
public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //绘制，因为是继承自View,不需要进行onLayout(),直接绘制就好了.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint paint = new Paint();
        //设置画笔颜色
        paint.setColor(Color.BLUE);
        //设置画笔样式
        paint.setStyle(Paint.Style.FILL);

        //创建要绘制的矩形
        Rect rect = new Rect(10, 10, 500, 500);

        //在画板上进行绘制
        canvas.drawRect(rect, paint);

        //绘制文字
        Paint paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(50);
        //绘制文字
        canvas.drawText("原谅时光记住爱",10,500,paintText);

        //注意画笔(画笔颜色)与画布(用画笔，绘制什么样的图形)



    }
}
