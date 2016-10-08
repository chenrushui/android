package com.crs.demo.ui.enumcase;

/**
 * Created on 2016/10/8.
 * Author:crs
 * Description:测试枚举类型
 * 枚举类型如何定义，如何使用，有什么注意事项
 *
 * enum关键字；因为也称之为枚举类，枚举类型名首字母要大写；枚举是java里面的特殊类；用于定义有限数量的数据集。
 * 1)声明一个枚举类；因为里面的值都是常量，所以大写,用逗号分隔。
 * 2)在编译时，枚举类型被编译成.class文件。
 * 3)枚举类中的每一个常量，都是其所在枚举的子类。
 * 4)当使用枚举常量时，jvm会自动创建该常量的实例: ColorEnum color = ColorEnum.RED;
 * 5)因为color值是颜色枚举类型，所以color值只能是定义的枚举常量中的一种，赋其他的值会报错，这就是所谓的限定变量的赋值。
 * 6)打印的color值为字符串，可以配合switch进行使用。
 * 7)values()是枚举类中特殊的方法，用于将枚举值作为数组返回。
 * 8)如果需要向枚举中添加新方法，那么必须在enum实例序列的最后添加一个分号。
 *
 * 1)枚举类型是一种数据类型，用于定义变量，限制变量的赋值(枚举值是确定的，有限的)。
 * 2)如何获取枚举值？通过"枚举名.值"来取得相关枚举中的值
 * 3)可以把相关的常量分组到一个枚举类型里，而且枚举提供了比常量更多的方法。
 */
public class testEnum {
    public static void main(String[] args) {
        //1)如何使用枚举
        ColorEnum color = ColorEnum.RED;
        //赋其他的值，会报错,限定变量的赋值
        //color="11";

        //2)获取枚举数组
        ColorEnum[] values = ColorEnum.values();
        for (ColorEnum e : values) {
            System.out.print(e);
        }

        //3)配合switch进行使用
        ColorEnum e = values[0];
        switch (e) {
            case RED: {
                System.out.print("red");
            }
            break;
            case BLUE: {
                System.out.print("blue");
            }
            break;
            case YELLOW: {
                System.out.print("yellow");
            }
            break;
        }
    }
}
