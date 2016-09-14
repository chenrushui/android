package com.crs.demo.utils;

import android.os.Handler;
import android.os.Looper;

import com.crs.demo.interfaces.BaseCallBack;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2016/8/30.
 * Author:crs
 * Description:OKHttp简单封装,练习对网络请求的封装
 * 单例模式，请求，请求结果回调
 */
public class OKHttpUtils {

    private static OkHttpClient client;
    private Gson gson;
    private Handler mHandle;

    public OKHttpUtils() {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(10, TimeUnit.SECONDS);
        gson = new Gson();

        mHandle = new Handler(Looper.getMainLooper());
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static OKHttpUtils getInstance() {
        return new OKHttpUtils();
    }

    /**
     * get请求
     *
     * @param url
     */
    public void get(String url, BaseCallBack callBack) {
        Request request = buildRequest(url, null, HttpMethodType.GET);
        doRequest(request, callBack);
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     */
    public void post(String url, HashMap<String, String> params, BaseCallBack callBack) {
        Request request = buildRequest(url, params, HttpMethodType.POST);
        doRequest(request, callBack);
    }


    /**
     * 发送网络请求
     *
     * @param request
     */
    public void doRequest(Request request, final BaseCallBack callBack) {

        //这个方法主要用于弹出加载进度对话框(方法的执行顺序)
        callBack.onRequestBefore(request);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //接口方法里面的参数，是有其他的方法参数决定的。
                callBack.onFailure(request, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {

                if (response.isSuccessful()) {
                    String json = response.body().string();
                    if (callBack.type == String.class) {
                        callBack.onSuccess(response, json);
                    } else {
                        try {
                            Object object = gson.fromJson(json, callBack.type);
                            callBack.onSuccess(response, object);
                        } catch (JsonParseException e) {
                            callBack.onError(response, response.code(), e);
                        }
                    }
                    callBack.onSuccess(response, null);
                } else {
                    callBack.onError(response, response.code(), null);
                }

            }
        });
    }

    /**
     * 创建一个请求对象
     *
     * @param url
     * @param params
     * @param httpMethodType
     * @return
     */
    private Request buildRequest(String url, HashMap<String, String> params, HttpMethodType httpMethodType) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (httpMethodType == HttpMethodType.GET) {
            builder.get();
        } else if (httpMethodType == HttpMethodType.POST) {
            RequestBody body = buildFormData(params);
            builder.post(body);
        }
        return builder.build();
    }

    /**
     * 遍历集合 拼接请求参数
     *
     * @param params
     * @return
     */
    private RequestBody buildFormData(HashMap<String, String> params) {
        FormEncodingBuilder body = new FormEncodingBuilder();
        if (params != null) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> entity : entries) {
                String key = entity.getKey();
                String value = entity.getValue();
                body.add(key, value);
            }
        }
        return body.build();
    }


    /**
     * 请求方式枚举类型
     */
    enum HttpMethodType {
        GET,
        POST
    }


    private void callBackSuccess(final BaseCallBack callBack, final Response response, final Object object) {
        mHandle.post(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess(response,object);
            }
        });
    }

}
