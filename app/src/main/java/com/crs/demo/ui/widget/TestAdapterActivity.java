package com.crs.demo.ui.widget;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.crs.demo.R;
import com.crs.demo.adapter.TestAdapter;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.base.BaseAdapter;
import com.crs.demo.utils.ToastUtils;


import java.util.ArrayList;

/**
 * Created on 2016/9/1.
 * Author:crs
 * Description:TestAdapterActivity
 * 测试对BaseAdapter的基本封装
 */
public class TestAdapterActivity extends BaseActivity {
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_adapter);

        getData();

        RecyclerView rv = findView(R.id.rv_test_adapter);

        rv.setLayoutManager(new LinearLayoutManager(this));

        TestAdapter testAdapter = new TestAdapter(list, this);
        rv.setAdapter(testAdapter);

        testAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                ToastUtils.showShort(TestAdapterActivity.this, position + "");
            }
        });
    }


    //准备要显示的数据
    private void getData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("测试数据" + i);
        }
    }
}
