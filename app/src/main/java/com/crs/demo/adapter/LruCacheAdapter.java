package com.crs.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.crs.demo.R;
import com.crs.demo.ui.lrucache.BitmapUtil;

import java.util.ArrayList;

/**
 * Created on 2016/10/9.
 * Author:crs
 * Description:LruCacheAdapter主要用于测试内存优化
 */
public class LruCacheAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<String> list;
    private final BitmapUtil mBitmapUtils;

    public LruCacheAdapter(Context mContext, ArrayList<String> list) {
        mInflater = LayoutInflater.from(mContext);
        this.list = list;
        mBitmapUtils = new BitmapUtil(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_viewpager_lrucache, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_item_viewpager_lrucache);
        mBitmapUtils.display(iv,list.get(position));
        return view;
    }
}
