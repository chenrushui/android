package com.crs.demo.ui.lrucache;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.widget.ListView;

import com.crs.demo.R;
import com.crs.demo.adapter.LruCacheAdapter;
import com.crs.demo.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created on 2016/10/9.
 * Author:crs
 * Description:LruCache的使用
 */
public class LruCacheActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrucache);

        initViews();
    }

    private void initViews() {
        ArrayList list = new ArrayList<>();
        list.add("http://img5.imgtn.bdimg.com/it/u=2296402476,2057547975&fm=21&gp=0.jpg");
        list.add("http://img.sdchina.com/news/20100604/c01_2e9c516d-cf8e-468b-a9a5-bc78bc277e71_3.jpg");
        list.add("http://p.ishowx.com/uploads/allimg/160823/415-160R3095959.jpg");

        ListView lv_test_lru_cache = findView(R.id.lv_test_lru_cache);
        LruCacheAdapter lruCacheAdapter = new LruCacheAdapter(this, list);
        lv_test_lru_cache.setAdapter(lruCacheAdapter);
    }

}
