package com.crs.demo.ui.widget;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;

import java.lang.ref.SoftReference;

/**
 * Created on 2016/10/9.
 * Author:crs
 * Description:软引用和弱引用在android中的使用
 */
public class TestReferenceActivity extends BaseActivity {

    //使用LruCache进行替换,参数为使用多大的内存去存储图片
    //花费虚拟机八分之一的空间去存储图片
    //使用LruCache比软引用更好，因为里面有一套算法，根据图片的使用频率去销毁图片。
    int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
    private LruCache<String, Bitmap> lruCacheMap = new LruCache<String, Bitmap>(maxSize) {
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_reference);

        String imageUrl=null;
        Bitmap bitmap = null;
        //使用软引用包裹获取到的bitmap
        SoftReference<Bitmap> softReference = new SoftReference<>(bitmap);
        lruCacheMap.put(imageUrl, bitmap);

        //通过url获取缓存
        Bitmap bit= lruCacheMap.get(imageUrl);
    }
}
