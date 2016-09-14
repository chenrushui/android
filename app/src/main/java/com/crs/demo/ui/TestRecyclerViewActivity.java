package com.crs.demo.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crs.demo.R;
import com.crs.demo.adapter.TestRecyclerAdapter;
import com.crs.demo.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:RecyclerView的使用 熟练掌握
 */
public class TestRecyclerViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView rv = findView(R.id.rv);

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add("测试数据" + i);
        }
        //先设置布局管理器，在设置适配器
        rv.setLayoutManager(new LinearLayoutManager(this));
        TestRecyclerAdapter testRecyclerAdapter = new TestRecyclerAdapter(this, list);
        rv.setAdapter(testRecyclerAdapter);
    }
}
