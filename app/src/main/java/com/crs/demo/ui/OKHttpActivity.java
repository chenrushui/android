package com.crs.demo.ui;

import android.os.Bundle;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.bean.OrderStatusEntity;
import com.crs.demo.constant.UrlConstant;
import com.crs.demo.interfaces.BaseCallBack;
import com.crs.demo.utils.LogUtils;
import com.crs.demo.utils.OKHttpUtils;
import com.crs.demo.utils.ToastUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.IllegalFormatCodePointException;

/**
 * Created on 2016/8/30.
 * Author:crs
 * Description:OKHttp的使用
 */
public class OKHttpActivity extends BaseActivity {
    private static final String TAG = "OKHttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok);

        //get请求
        //getOrderStatus();
        //post请求
        //getOrderStatusPost();

        //使用okHttpUtils进行网络请求
        okHttpUtilsGet();


    }

    private void okHttpUtilsGet() {
        OKHttpUtils okHttpUtils = new OKHttpUtils();
        okHttpUtils.get(UrlConstant.ORDER_STATUS, new BaseCallBack<OrderStatusEntity>() {
            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, final OrderStatusEntity orderStatusEntity) {

            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });


    }

    private void getOrderStatusPost() {
        //类名: OkHttpClient  RequestBody  FormEncodingBuilder  Request  newCall()  enqueue()
        String url = UrlConstant.ORDER_STATUS_POST;
        OkHttpClient client = new OkHttpClient();
        //用于拼接请求参数
        RequestBody body = new FormEncodingBuilder().add("orderNo", "TH01587458").build();
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showLong(OKHttpActivity.this, json);
                        }
                    });
                }

            }
        });

    }

    private void getOrderStatus() {
        String url = UrlConstant.ORDER_STATUS;
        //创建请求客户端 类名OkHttpClient  方法newCall()
        OkHttpClient client = new OkHttpClient();
        //创建请求对象对象，并传递请求url 类名Request
        Request request = new Request.Builder().url(url).build();
        try {
            //异步请求并进行请求结果回调
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        final String json = response.body().string();
                        LogUtils.i(TAG, json);
                        //可以拿到就送数据 但是结果在子线程里面

                        //在子线程里更新UI
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showLong(OKHttpActivity.this, json);
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
