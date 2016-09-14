package com.crs.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crs.demo.R;

import java.util.ArrayList;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:RecyclerView的adapter
 * 1)自定义Adapter继承自RecyclerView.Adapter 记住是哪个包下的adapter.RecyclerView.Adapter是抽象类，必须实现其中的抽象方法.
 * 2)给RecyclerView.Adapter设置泛型类型，为<自定义adapter.自定义Viewholder>(固定格式)
 * 3)自定义ViewHolder继承自RecyclerView.ViewHolder，记住是RecyclerView下的ViewHolder
 * 4)onCreateViewHolder()方法的返回值类型为<自定义adapter.自定义Viewholder> onBindViewHolder()方法里面的holder类型也是<自定义adapter.自定义Viewholder>
 * 要不然找不到控件的
 * 5)定义的ViewHolder是一个内部类，RecyclerView.Adapter在继承时需要指定泛型.要制定ViewHolder是谁的内部类.
 * 6)记住这三个方法onCreateViewHolder() onBindViewHolder() getItemCount() LinearLayoutManager RecyclerView.Adapter  RecyclerView.ViewHolder
 * 7)itemView 指代当前条目。需要一个ViewHolder，就创建并返回。
 * 8)RecyclerView的使用，先设置布局管理器，在设置适配器。
 */
public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.ViewHolder> {
    private ArrayList<String> list;
    private LayoutInflater mInflate;

    public TestRecyclerAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        mInflate = LayoutInflater.from(context);
    }

    @Override
    public TestRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item_test_recycler, null);
        ViewHolder myHolder = new ViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(TestRecyclerAdapter.ViewHolder holder, int position) {
        String title = list.get(position);
        holder.tv.setText(title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
