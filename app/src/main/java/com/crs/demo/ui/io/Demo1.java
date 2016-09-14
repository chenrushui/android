package com.crs.demo.ui.io;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created on 2016/8/25.
 * Author:crs
 * Description:测试文件流
 */
public class Demo1 {
    public static void main(String[] args) {
        //需求：向a.txt中写入数据“原谅时光记住爱”
        try {
            File file = new File("a.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("原谅时光记住爱".getBytes());
            fos.close();

            //上面的代码做了什么事情
            //1)创建一个a.txt文件：如果文件存在就打开，如果文件不存在，就创建打开。
            //2)创建一个FileOutputStream 输出流对象。
            //3）把对象赋值给变量。

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
