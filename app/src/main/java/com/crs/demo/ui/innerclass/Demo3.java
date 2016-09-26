package com.crs.demo.ui.innerclass;

/**
 * Created on 2016/9/26.
 * Author:crs
 * Description:静态内部类的使用以及注意事项
 */
public class Demo3 {
    private int age = 16;
    private static int score = 16;

    static class Inner {
        //1)在变量前面加private 表示类的私有属性，只可以在类内部访问使用，即只能在本类中进行使用，也不能够在子类中使用，子类只能继承公有的成员变量和成员方法。
        //2)一定要区分好匿名对象与内部类的区别。
        public void print() {
            //4)如果用static 将内部内静态化，那么内部类就只能访问外部类的静态成员变量，具有局限性.
            //System.out.print(age);会报错。
            System.out.print(score);
        }
    }

}
