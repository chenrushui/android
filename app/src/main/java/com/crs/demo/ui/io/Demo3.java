package com.crs.demo.ui.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created on 2016/8/25.
 * Author:crs
 * Description:测试文件流
 */
public class Demo3 {
    public static void main(String[] args) {
        //需求:把b:\\a.txt 复制到e：\\b.txt
        //实现：
        try {
            FileInputStream fis = new FileInputStream("D:\\a.txt");
            FileOutputStream fos = new FileOutputStream("D:\\b.txt");
            //每次读取的字节数
            int len;
            while ((len = fis.read()) != -1) {
                fos.write(len);
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
