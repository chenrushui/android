package com.crs.demo.ui.io;

import android.app.Dialog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created on 2016/8/25.
 * Author:crs
 * Description:测试高效的文件流
 */
public class Demo5 {
    public static void main(String[] args) {
        //method1();
        method2();
    }

    private static void method2() {
        //使用高效的流从文件中读取数据，并显示出来，如何实现？
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d.txt"));
            int read = bis.read();
            System.out.print((char) read);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void method1() {
        try {
            //把数据写入到这个文件中
            FileOutputStream fos = new FileOutputStream("d.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write("天南地北双飞客、老刺几回寒暑".getBytes());
            bos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
