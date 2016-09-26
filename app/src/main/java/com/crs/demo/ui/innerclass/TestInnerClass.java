package com.crs.demo.ui.innerclass;

/**
 * Created on 2016/9/26.
 * Author:crs
 * Description:测试内部类的使用
 */
public class TestInnerClass {
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        //创建内部类对象
        Demo1.Inner inner = demo1.new Inner();
        inner.print();

        //主要用于测试内部类的变量访问形式
        Demo2 demo2 = new Demo2();
        Demo2.Inner inner1 = demo2.new Inner();
        inner1.print();

        //测试静态内部类的使用
        Demo3.Inner inner3= new Demo3.Inner();
        inner3.print();
    }
}
