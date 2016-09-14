package com.crs.demo.utils;

import java.util.LinkedList;

/**
 * Created on 2016/8/24.
 * Author:crs
 * Description:利用LinkedList模拟栈数据结构
 */
public class MyStack {
    //使用LinkedList存储数据
    private LinkedList<String> list;

    public MyStack() {
        this.list = new LinkedList();
    }

    public void pop(String str) {
        list.addFirst(str);
    }

    public String get() {
        String first = list.removeFirst();
        return first;
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}
