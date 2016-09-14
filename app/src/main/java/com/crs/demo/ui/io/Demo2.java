package com.crs.demo.ui.io;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created on 2016/8/25.
 * Author:crs
 * Description:测试文件输入流
 */
public class Demo2 {
    public static void main(String[] args) {
        //把数据读取到内存中，并进行输出。
        try {
            File file = new File("a.txt");
            FileInputStream fis = new FileInputStream(file);
            //每次读取一个字节数组
            //byte[] bytes = new byte[1024];
            int len ;
            while ((len = fis.read()) != -1) {
                System.out.print((char) len);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
