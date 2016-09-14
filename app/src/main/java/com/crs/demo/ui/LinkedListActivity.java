package com.crs.demo.ui;

import android.os.Bundle;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.utils.LogUtils;
import com.crs.demo.utils.MyStack;

import java.util.LinkedList;

/**
 * Created on 2016/8/24.
 * Author:crs
 * Description:LinkedList集合的特点
 * * 特有方法：
 * public void addFirst(E e)将指定元素插入此列表的开头
 * public void addLast(E e)将指定元素添加到此列表的结尾。
 * public E getFirst()返回此列表的第一个元素。
 * public E getLast()返回此列表的最后一个元素
 * public E removeFirst()移除并返回此列表的第一个元素。
 * public E removeLast()移除并返回此列表的最后一个元素。
 */
public class LinkedListActivity extends BaseActivity {


    private static final String TAG = "LinkedListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkedlist);

        LinkedList<String> list = new LinkedList<String>();
        list.add("chen1");
        list.add("chen2");
        list.add("chen3");
        list.add("chen4");

        for (String str : list) {
            LogUtils.i(TAG, str.toString());
        }
        LogUtils.i(TAG, "----------------------获取---------------------");
        LogUtils.i(TAG, list.getFirst());
        LogUtils.i(TAG, list.getLast());
        LogUtils.i(TAG, "----------------添加头尾的元素-------------------");
        list.addFirst("?");
        list.addLast(":");
        LogUtils.i(TAG, list.getFirst());
        LogUtils.i(TAG, list.getLast());
        LogUtils.i(TAG, "------------------删除头尾的元素----------------");
        list.removeFirst();
        list.removeLast();
        LogUtils.i(TAG, list.getFirst());
        LogUtils.i(TAG, list.getLast());

        //使用LinkedList模拟栈数据结构
        LogUtils.i(TAG, "------------------使用LinkedList模拟栈数据结构：先进后出----------------");

        MyStack myStack = new MyStack();//3,2,1
        myStack.pop("1");
        myStack.pop("2");
        myStack.pop("3");

        while (!myStack.isEmpty()) {
            String s = myStack.get();
            LogUtils.i(TAG, s);
        }
    }


}
