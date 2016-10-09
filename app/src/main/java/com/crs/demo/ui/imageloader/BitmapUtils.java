package com.crs.demo.ui.imageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.crs.demo.constant.SDConstant;
import com.crs.demo.utils.MD5Utils;
import com.crs.demo.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2016/9/28.
 * Author:crs
 * Description:网络图片加载工具类
 * 图片三级缓存:内存缓存、SD卡缓存、网络缓存
 * Runnable是线程类的接口
 * setImageBitmap()方法
 * 发送空消息，要有消息标识：mHandler.sendEmptyMessage(FAILURE_LOAD_DATA);sendEmptyMessage()
 * 如何把文件流解析成图片BitmapFactory
 * 网络加载后要进行sdcard存储、内存存储，把所有的图片缓存到一个指定的目录下
 * 图片压缩bitmap.compress( ); 参数如何理解？
 * 网络加载大图，只要不显示就不会报OOM异常。
 * "/sdcard/imageCache"对此路径的理解
 *
 * java中的对象的引用类型的理解
 * 软引用类的使用 SoftReference  如何使用，构造函数传递一个对象
 * 弱引用类的使用 WeakReference
 */
public class BitmapUtils {
    private Context mContext;

    private File cacheDir = new File(SDConstant.imageCache);

    private static final int SUCCESS_LOAD_DATA = 0;
    private static final int FAILURE_LOAD_DATA = 1;
    private InnerHandler mHandler = new InnerHandler();
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    //使用集合来存储图片
    //private HashMap<String, SoftReference<Bitmap>> map = new HashMap<>();

    //使用LruCache进行替换,参数为使用多大的内存去存储图片
    //花费虚拟机八分之一的空间去存储图片
    //使用LruCache比软引用更好，因为里面有一套算法，根据图片的使用频率去销毁图片。
    int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
    private LruCache<String, Bitmap> map = new LruCache<String, Bitmap>(maxSize) {
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight();
        }
    };


    public BitmapUtils(Context context) {
        this.mContext = context;
        //如果缓存目录不存在，就进行创建
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }
    }

    //显示图片
    public void display(ImageView iv, String url) {
        //内存加载
        Bitmap bitmap = getBitmapFromMemory(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
        } else {
            //SD卡加载
            bitmap = getBitmapFromSdcard(url);
            if (bitmap != null) {
                iv.setImageBitmap(bitmap);
            } else {
                //网络加载
                getBitmapFromInternet(iv, url);
            }
        }
    }

    private Bitmap getBitmapFromMemory(String url) {
//        Bitmap bitmap = null;
//        SoftReference<Bitmap> bitmapSoftReference = map.get(url);
//        if (bitmapSoftReference != null) {
//            bitmap = bitmapSoftReference.get();
//        }
        Bitmap bitmap = map.get(url);
        return bitmap;

    }

    private Bitmap getBitmapFromSdcard(String url) {
        String pathName = MD5Utils.encode(url);
        File file = new File(cacheDir, pathName);

        BitmapFactory.Options opts = new BitmapFactory.Options();
        //只去加载图片的宽高
        opts.inJustDecodeBounds = true;
        int sampleSize = calcImageSample(opts);
        BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
        //加载图片的内容
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = sampleSize;
        //加载图片的操作
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);

        //执行内存存储
        //map.put(url,bitmap);

        return bitmap;
    }

    private void getBitmapFromInternet(ImageView iv, String url) {
        //开启线程，加载图片
        //创建对象，传入参数，构造方法
        //缺陷是：如果需要加载100张图片，就需要创建100个线程对象去执行任务
        //new Thread(new DownloadImageTask(iv, url)).start();

        //使用线程池进行改良：让线程复用
        //创建一个线程池对象，里面默认开启五条线程，能够重复使用线程池里面的线程
        executorService.submit(new DownloadImageTask(iv, url));

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

                    //进行sd卡缓存
                    //1)把图片路径经过MD5加密，当做文件名
                    String fileName = MD5Utils.encode(imageUrl);
                    //file表示缓存文件，指向某一个具体的文件
                    File file = new File(cacheDir, fileName);
                    //2)现在的问题是如何把图片写入到指定的文件中
                    OutputStream os = new FileOutputStream(file);
                    //图片质量 以什么格式  图片质量
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);

                    //使用软引用执行内存缓存
                    SoftReference<Bitmap> softReference = new SoftReference<>(bitmap);
                    map.put(imageUrl, bitmap);

                    //封装对象进行传递
                    ImageViewBitmap imageViewBitmap = new ImageViewBitmap();
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
                    ImageViewBitmap imageViewBitmap = (ImageViewBitmap) msg.obj;
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

    //计算图片的缩放比例
    private int calcImageSample(BitmapFactory.Options opts) {
        int sampleSize;
        //获取图片的宽高
        int width = opts.outWidth;
        int height = opts.outHeight;
        //获取手机的屏幕宽度,单位为像素
        Resources resources = mContext.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels = displayMetrics.widthPixels;
        int scaleW = width / widthPixels;
        int scaleH = height / heightPixels;
        //取缩放的最大比例
        sampleSize = scaleW > scaleH ? scaleW : scaleH;
        return sampleSize;
    }
}
