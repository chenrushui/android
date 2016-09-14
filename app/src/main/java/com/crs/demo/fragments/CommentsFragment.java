package com.crs.demo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crs.demo.R;
import com.crs.demo.base.BaseFragment;

/**
 * Created on 2016/9/14.
 * Author:crs
 * Description:评论fragment
 */
public class CommentsFragment extends BaseFragment {
    private String type;
    private boolean isPay;
    private String pid;
    private int buyNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getArgumentsData();
        getNetData();


    }

    private void getNetData() {
        //杨哥没有在Activity中进行网络请求，都是在各自的fragment中进行的网络请求
        //请求网络数据
    }

    private void getArgumentsData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
            isPay = bundle.getBoolean("isPay");
            pid = bundle.getString("pid");
            buyNum = bundle.getInt("buyNum");
        }
    }


}
