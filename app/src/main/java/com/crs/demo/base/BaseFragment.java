package com.crs.demo.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 2016/9/14.
 * Author:crs
 * Description:抽取基本的fragment
 */
public class BaseFragment extends Fragment {
    public Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //实例化控件
    public <T extends View> T getView(View view, int id) {
        if (view == null) {
            return null;
        }
        return (T) (view.findViewById(id));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mActivity != null) {
            mActivity = null;
        }
    }


}
