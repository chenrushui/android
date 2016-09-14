package com.crs.demo.ui.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created on 2016/8/25.
 * Author:crs
 * Description:测试文件流
 */
public class DemoEnd6 {
    //需求：使用字节流四种方式复制mp3文件，并计算复制所需的时间。
    //基本的流: 一次一个字节，一次一个字节数组
    //高效的流: 一次一个字节，一次一个字节数组

    //步骤:1)封装数据源、2)封装目的地、3)读取数据、4)写入数据、5)关闭流、
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //method1();
        //method2();
        //method3();
        method4();

        long end = System.currentTimeMillis();
        System.out.print("共耗时" + (end - start) + "毫秒");


    }

    private static void method4() {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:\\1.avi"));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("4.avi"));
            //每次读取和写入一个字节数组
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            //其实是释放与文件读写相关的硬件资源。
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void method3() {
        try {
            //使用高效的流进行文件读写
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:\\1.avi"));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("3.avi"));
            int by = bis.read();
            while (by != -1) {
                bos.write(by);
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void method2() {
        //每次一个字节数组
        try {
            FileInputStream fis = new FileInputStream("D:\\1.avi");
            FileOutputStream fos = new FileOutputStream("2.avi");
            //创建字节数组，每次读取和写入一千字节
            byte[] bytes = new byte[1024];
            int len;
            //每次读取一个字节数组
            while ((len = fis.read(bytes)) != -1) {
                //最后一次可能不是一个字节数组
                fos.write(bytes, 0, len);
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void method1() {
        try {
            FileInputStream fis = new FileInputStream("D:\\1.avi");
            FileOutputStream fos = new FileOutputStream("1.avi");
            //读取数据，每次一个字节
            int by = fis.read();
            while (by != -1) {
                //写入数据，每次一个字节
                fos.write(by);
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
