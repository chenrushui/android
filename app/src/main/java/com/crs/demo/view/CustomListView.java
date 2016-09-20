package com.crs.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created on 2016/9/20.
 * Author:crs
 * Description:自定义ListView解决在ScrollView中的滑动冲突
 */
public class CustomListView extends ListView {
    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置该View本身的大小
     *
     * 表示ListView控件的长宽以及空间描述的元数据
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //子元素最多达到指定大小的高度值
        int expandSpecHeight = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpecHeight);

        //获取宽度的测量模式mode和测量值size
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        //获取高度的测量模式mode和测量值size
        int modeHeight = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(sizeWidth,sizeHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    /**
     * 1)MeasureSpc类封装了父View传递给子View的布局layout要求,每个MeasureSpc实例代表宽度或者高度.
     *
     * 2)MeasureSpc表示测量模式，有三种基本类型的测量模式
     *       ①、UNSPECIFIED(未指定测量模式)，父控件不对子控件施加任何束缚，子控件可以得到任意想要的大小；unspecified
     *       此时对应的布局参数的设置为：android:layout_width="wrap_content"  android:layout_height="wrap_content"
     *
     *       ②、EXACTLY(完全)，父控件决定子控件的的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
     *       此时对应的布局参数的设置为：android:layout_width="100dp"  android:layout_height="100dp"
     *       父控件只给定了100dp的长宽供子控件布局
     *
     *       ③、AT_MOST(至多)，子元素最多达到指定大小的值。
     *        此时对应的布局参数的设置为：android:layout_width="match_parent"  android:layout_height="match_parent"
     *
     * 3)View想要在屏幕上显示出来要先经过measure(计算)和layout(布局).
     * 当控件的父元素正要放置该控件时调用.父元素会问子控件一个问题，“你想要用多大地方啊？”，然后传入两个参数
     * ——widthMeasureSpec和heightMeasureSpec.
     * 这两个参数指明控件可获得的空间以及关于这个空间描述的元数据.(可以获取的空间大小以及对这个控件的描述)
     *
     * 4)宽度、高度都是有测量模式和测量值的
     * 参数widthMeasureSpec与heightMeasureSpec都有两重含义
     * int mode = MeasureSpec.getMode(widthMeasureSpec)得到模式，
     * int size = MeasureSpec.getSize(widthMeasureSpec)得到尺寸。
     * mode总共有三种(测量模式)
     *
     * 5)makeMeasureSpec()方法的作用:根据提供的控件高度值和模式创建一个测量值(测量格式、测量规格)
     * makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST)
     * 参数1表示测量值，参数2表示测量模式，此类中只改变了自定义ListView的高度
     *
     * 6)super.onMeasure(widthMeasureSpec, expandSpec);
     * 传递给父类的onMeasure()方法,因为子控件的大小和位置是由父控件决定的
     *
     * 其实super.onMeasure(widthMeasureSpec, expandSpec)与setMeasuredDimension(sizeWidth,sizeHeight)的作用是相同的
     *
     */
}
