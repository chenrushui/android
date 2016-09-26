package com.crs.demo.ui.innerclass;

/**
 * Created on 2016/9/26.
 * Author:crs
 * Description:1)内部类可以直接使用外部类的成员变量，在android 点击事件跳转页面中有用到，页面之间传递参数。
 */
public class Demo1 {
    private int age = 12;

    class Inner {
        //内部类中提供一个打印方法
        public void print() {
            System.out.print(age);
        }
    }

}
