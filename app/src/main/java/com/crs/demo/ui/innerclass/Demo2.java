package com.crs.demo.ui.innerclass;

/**
 * Created on 2016/9/26.
 * Author:crs
 * Description:内部类成员变量使用特点
 */
public class Demo2 {
    private int age = 12;

    class Inner {
        int age = 13;

        void print() {
            int age = 14;
            System.out.print(age + "\n");

            //2)如果存在同名变量，访问内部类本身的成员变量可用this.属性名
            System.out.print(this.age + "\n");

            //3)如果存在同名变量，访问外部类的成员变量需要使用Out.this.属性名
            System.out.print(Demo2.this.age + "\n");
        }
    }
}
