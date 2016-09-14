package com.crs.demo.ui.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created on 2016/8/25.
 * Author:crs
 * Description:测试文件流
 */
public class Demo4 {
    public static void main(String[] args) {
        //模板性的代码
        //每次读取一个字节数组,需要建立一个字节数组。
        try {
            FileInputStream fis = new FileInputStream("a.txt");
            FileOutputStream fos = new FileOutputStream("c.txt");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
