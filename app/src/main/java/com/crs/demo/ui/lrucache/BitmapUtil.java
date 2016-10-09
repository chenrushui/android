package com.crs.demo.ui.lrucache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.crs.demo.utils.ToastUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2016/10/9.
 * Author:crs
 * Description:封装LruCache的使用
 */
public class BitmapUtil {
    private Context mContext;
    private static final int SUCCESS_LOAD_DATA = 0;
    private static final int FAILURE_LOAD_DATA = 1;
    //LruCache实例，用于存储Bitmap
    private LruCache<String, Bitmap> mLruCache;
    //线程池管理类
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    //创建主线程的消息对象
    private BitmapUtil.InnerHandler mHandler = new BitmapUtil.InnerHandler();

    public BitmapUtil(Context context) {
        this.mContext = context;
        //总的可用内存
        int mTotalSize = (int) Runtime.getRuntime().totalMemory();
        mLruCache = new LruCache<String, Bitmap>(mTotalSize / 8) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    public void display(ImageView iv, String url) {
        Bitmap bitmap = getBitmapFromMemory(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
        } else {
            getBitmapFromInternet(iv, url);
        }
    }

    private void getBitmapFromInternet(ImageView iv, String url) {
        executorService.submit(new BitmapUtil.DownloadImageTask(iv, url));
    }

    private Bitmap getBitmapFromMemory(String url) {
        return mLruCache.get(url);
    }

    //加载网络图片的线程类
    private class DownloadImageTask implements Runnable {
        private String imageUrl;
        //如何把此ImageView对象传递到Handler中
        private ImageView iv;
        private HttpURLConnection conn;

        public DownloadImageTask(ImageView iv, String url) {
            this.imageUrl = url;
            this.iv = iv;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(3000);
                if (conn.getResponseCode() == 200) {
                    InputStream is = conn.getInputStream();
                    //此时bitmap对象在子线程里面
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    //使用LruCache执行内存缓存
                    mLruCache.put(imageUrl, bitmap);
                    //封装对象进行传递
                    BitmapUtil.ImageViewBitmap imageViewBitmap = new BitmapUtil.ImageViewBitmap();
                    imageViewBitmap.bitmap = bitmap;
                    imageViewBitmap.iv = iv;
                    Message message = mHandler.obtainMessage(SUCCESS_LOAD_DATA, imageViewBitmap);
                    message.sendToTarget();
                    is.close();
                } else {
                    mHandler.sendEmptyMessage(FAILURE_LOAD_DATA);
                }
            } catch (Exception e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(FAILURE_LOAD_DATA);
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }
    }

    //传递消息的类
    private class InnerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS_LOAD_DATA: {
                    BitmapUtil.ImageViewBitmap imageViewBitmap = (BitmapUtil.ImageViewBitmap) msg.obj;
                    Bitmap bitmap = imageViewBitmap.bitmap;
                    ImageView iv = imageViewBitmap.iv;
                    iv.setImageBitmap(bitmap);
                }
                break;
                case FAILURE_LOAD_DATA: {
                    ToastUtils.showShort(mContext, "图片加载异常");
                }
                break;
            }
        }
    }

    //把两者封装成一个对象进行传递
    private static class ImageViewBitmap {
        ImageView iv;
        Bitmap bitmap;
    }

}
