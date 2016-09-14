package com.crs.demo.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:BaseViewHolder
 * 1)如何去获取控件以及确定控件类型，现在不清楚控件的类型以及数量.不能把控件写死，通过View数组来实现
 * 2)<T extends View> T 定义返回值的泛型类型;在返回的时候，进行强转
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //用于存储条目中的控件
    private final SparseArray<View> views;
    private BaseAdapter.OnItemClickListener onItemClickListener;

    //父类中只有有构造函数，子类在继承父类时，必须添加对应的构造函数
    public BaseViewHolder(View itemView, BaseAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        views = new SparseArray<>();
        //为条目设置点击事件
        itemView.setOnClickListener(this);
    }

    //实例化控件
    private <T extends View> T findView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    //对外提供公共的方法，去实例化控件
    public View getViews(int id) {
        return findView(id);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.OnItemClick(v,getLayoutPosition());
        }
    }
}
