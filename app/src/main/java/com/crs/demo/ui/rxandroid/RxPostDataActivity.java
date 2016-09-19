package com.crs.demo.ui.rxandroid;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.constant.UrlConstant;
import com.crs.demo.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/9/19.
 * Author:crs
 * Description:使用RxAndroid+OKHttp进行查询数据的操作
 */
public class RxPostDataActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_rx_post_data;
    private EditText et_rx_post_data;
    private QueryUtils mQueryUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_post_data);

        initViews();
        initListener();

        //持有业务逻辑类的引用
        mQueryUtils = new QueryUtils();


    }


    private void initViews() {
        btn_rx_post_data = findView(R.id.btn_rx_post_data);
        et_rx_post_data = findView(R.id.et_rx_post_data);

    }

    private void initListener() {
        btn_rx_post_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rx_post_data: {
                String orderId = et_rx_post_data.getText().toString().trim();
                if (TextUtils.isEmpty(orderId)) {
                    return;
                }

                mQueryUtils.queryOrder(UrlConstant.ORDER_STATUS_POST, orderId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {
                                //服务器端返回的为json字符串
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    int code = jsonObject.getInt("Code");
                                    if (code == 1) {
                                        ToastUtils.showShort(RxPostDataActivity.this, "查询成功");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


            }
            break;
        }

    }
}

