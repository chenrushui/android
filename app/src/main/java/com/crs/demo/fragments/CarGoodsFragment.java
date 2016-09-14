package com.crs.demo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crs.demo.R;
import com.crs.demo.base.BaseFragment;
import com.crs.demo.bean.ResponseEntity;
import com.crs.demo.ui.testFragmentActivity;

/**
 * Created on 2016/9/14.
 * Author:crs
 * Description:车品fragment
 */
public class CarGoodsFragment extends BaseFragment {

    private String type;
    private boolean isPay;
    private String pid;
    private int buyNum;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_goods, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //处理数据
        getArgumentsData();
        ((testFragmentActivity) mActivity).getCarGoodsData();
    }

    //获取传递过来的数据
    private void getArgumentsData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
            isPay = bundle.getBoolean("isPay");
            pid = bundle.getString("pid");
            buyNum = bundle.getInt("buyNum");
        }

        getNetData.getCarGoodsData(bundle);
    }


    //从Activity向Fragment传递数据
    public static CarGoodsFragment newInstance(String type, boolean isPay, String pid, int buyNum) {
        CarGoodsFragment carGoodsFragment = new CarGoodsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putBoolean("isPay", isPay);
        bundle.putString("pid", pid);
        bundle.putInt("buyNum", buyNum);
        carGoodsFragment.setArguments(bundle);
        //返回fragment
        return carGoodsFragment;
    }

    //接口对调 从Fragment向Activity传递数据
    public GetNetData getNetData;

    public interface GetNetData {
        void getCarGoodsData(Bundle bundle);
    }

    public void getData(GetNetData getNetData) {
        this.getNetData = getNetData;
    }

    //获取网络请求的数据
    public void getResult(ResponseEntity response) {
        if (response != null) {
            //进行对控件的赋值操作
        }
    }


}
