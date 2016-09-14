package com.crs.demo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseAdapter;
import com.crs.demo.base.BaseViewHolder;

import java.util.List;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:TestAdapter
 * 1)在继承的时候指定数据类型和BaseViewHolder类型
 * 2)在自定义的adapter中哪些是变化的: 条目布局、集合里面存放的数据类型、控件数量以及控件类型
 * 3)让自定义的Adapter实现抽象方法(数据绑定通过抽象方法来实现)
 */
public class TestAdapter extends BaseAdapter<String, BaseViewHolder> {


    public TestAdapter(List<String> list, Context context) {
        //需要传递一个条目布局进去
        super(list, context, R.layout.item_test_adapter);
    }


    @Override
    public void bindData(String s, BaseViewHolder holder) {
        TextView tv = (TextView) holder.getViews(R.id.tv);
        tv.setText(s);
    }
}
