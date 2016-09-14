package com.crs.demo.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:BaseAdapter
 * 1)在创建继承自BaseAdapter的类的时候，指定传递的数据类型和ViewHolder类型
 * 2)抽象方法中为什么药传递holder参数，因为要实例化控件.
 * 3)定义接口，添加条目点击事件的公共回调方法，接口里面的抽象方法什么时候调用？ 条目点击的时候，条目点击在BaseViewHolder里面
 * 所以必须通过构造函数传递过去.
 * 4)在不确定类型的时候，使用泛型来标识.每次传递的数据类型不确定，所以使用泛型。
 * 5)抽取公共的事情，让父类来做，比如:根据id创建view，条目的点击事件，绑定数据.
 * 6)基类里面提供常用的方法。
 * 7)抽取与封装是不一样的。 向上抽去基类。(子类原来越少，而父类越来越多)
 */
public abstract class BaseAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {
    //由于需要让子类继承，所以不能私有.
    public List<T> list;
    public LayoutInflater mInflate;
    public int mLayoutResId;

    //初始化父类的构造方法，让子类创建对象的时候，调用
    public BaseAdapter(List<T> list, Context context, int mLayoutResId) {
        this.list = list;
        this.mInflate = LayoutInflater.from(context);
        this.mLayoutResId = mLayoutResId;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflate.inflate(mLayoutResId, null);
        //在构造方法里面传递接口对象
        BaseViewHolder baseViewHolder = new BaseViewHolder(view, onItemClickListener);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        //获取数据对象
        T t = list.get(position);
        //通过抽象方法对调进行对外暴露，用于绑定数据，让子类实现(数据、holder代表控件)
        bindData(t, holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //定义一个抽象方法，让子类实现
    public abstract void bindData(T t, BaseViewHolder holder);

    //条目点击事件，接口回调。
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
