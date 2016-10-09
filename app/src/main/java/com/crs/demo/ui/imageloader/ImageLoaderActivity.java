package com.crs.demo.ui.imageloader;

import android.os.Bundle;
import android.widget.ImageView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 2016/9/28.
 * Author:crs
 * Description:自己写图片加载框架
 * 1)软引用的使用
 * 2)线程池的使用
 * 3)LruCache的使用
 * 4)图片缓存
 * URL:用于封装url,，打开一个网络请求连接对象
 * HttpURLConnection类：网络请求类,设置网络请求方式、设置连接超时时间、获取响应码、获取输入流对象(别忘了关闭流)、关闭连接对象
 * 网络加载后要进行sdcard存储、内存存储
 */

public class ImageLoaderActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);

        initViews();
        //getImageData();
    }

    private void initViews() {
        ImageView iv_image_load = findView(R.id.iv_image_load);
        BitmapUtils bitmapUtils = new BitmapUtils(this);
        bitmapUtils.display(iv_image_load, "http://h.hiphotos.baidu.com/image/pic/item/0eb30f2442a7d93327942977af4bd11373f001f2.jpg");

    }

    private void getImageData() {
        //加载图片是耗时操作，所以开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                String path = "";
                try {
                    URL url = new URL(path);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setRequestMethod("GET");
                    //如果响应码为200，说明请求成功
                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        //获取一个字节输出流对象
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] bytes = new byte[1024];
                        int length = 0;
                        while ((length = is.read(bytes)) != -1) {
                            //说明每次都能够读取到数据
                            bos.write(bytes, 0, length);
                        }
                        //转化成字符串
                        String json = bos.toString();
                        is.close();
                        bos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        //断开连接
                        conn.disconnect();
                    }
                }

            }
        }).start();

    }
}
