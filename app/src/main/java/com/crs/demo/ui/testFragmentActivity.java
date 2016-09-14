package com.crs.demo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.bean.ResponseEntity;
import com.crs.demo.fragments.CarGoodsFragment;
import com.crs.demo.fragments.CommentsFragment;

import java.util.ArrayList;

/**
 * Created on 2016/9/14.
 * Author:crs
 * Description:测试fragment
 */
public class testFragmentActivity extends BaseActivity implements CarGoodsFragment.GetNetData {

    private ArrayList<Fragment> fragmentsList;
    private CarGoodsFragment carGoodsFragment;
    private ResponseEntity response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);


        initViews();
        initFragments();

    }

    private void initViews() {
        ViewPager vp = findView(R.id.vp_test_fragment);
        //这个方法的作用：缓存数据还是重新加载数据
        vp.setOffscreenPageLimit(3);
        //此参数用于指定ViewPager预加载的页数，每次预加载前后三页，默认每次预加载前后一页。
    }

    private void initFragments() {
        fragmentsList = new ArrayList<>();

        //目的是获取fragment的实例，在创建fragment的时候传递数据
        carGoodsFragment = CarGoodsFragment.newInstance("普通车品", false, "1001", 10);
        fragmentsList.add(carGoodsFragment);
        //设置接口回调传递数据
        carGoodsFragment.getData(this);

        //------------------------------------------------------------------------
        //评论fragment
        CommentsFragment commentsFragment = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", "普通车品");
        bundle.putBoolean("isPay", false);
        bundle.putString("pid", "1001");
        bundle.putInt("buyNum", 10);
        commentsFragment.setArguments(bundle);

    }

    @Override
    public void getCarGoodsData(Bundle bundle) {

    }


    //请求网络接口，获取数据
    public void getCarGoodsData() {
        //请求到数据之后,把数据传递到carGoodsFragment页面中
        carGoodsFragment.getResult(response);
    }
}
