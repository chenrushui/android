package com.crs.demo.ui.okhttp;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.crs.demo.constant.IntConstant;
import com.crs.demo.utils.ConnUtils;
import com.crs.demo.utils.ToastUtils;
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
 * Created on 2016/9/19.
 * Author:crs
 * Description:网络请求工具类的封装
 * 注意事项：
 * 1）把请求结果回调到Activity中
 * 2）OKHttp的两部主要操作，创建Request，newCall()
 * 3）主线程与子线程通讯的实现
 * 4）发送网络请求前，判断网络连接状况
 * 5）当Activity关闭，网络请求也关闭
 */
public class HttpUtils {

    private OkHttpClient client;
    private Handler mHandler;
    private Activity mActivity;

    public HttpUtils(Activity mActivity) {
        client = new OkHttpClient();

        //设置连接超时时间,在网络正常的时候有效
        client.setConnectTimeout(IntConstant.REQUEST_TIME_OUT, TimeUnit.SECONDS);
        //设置读取数据的超时时间
        client.setReadTimeout(IntConstant.REQUEST_TIME_OUT, TimeUnit.SECONDS);
        //设置写入数据的超时时间
        client.setWriteTimeout(IntConstant.REQUEST_TIME_OUT, TimeUnit.SECONDS);

        this.mActivity = mActivity;

        //Looper.getMainLooper()  获取主线程的消息队列
        //默认情况下Handler会与其被定义时所在线程的Looper绑定，比如，Handler在主线程中定义，那么它是与主线程的Looper绑定。
        mHandler = new Handler(Looper.getMainLooper());

    }


    /**
     * get请求
     *
     * @param url
     * @param baseCallBack
     */
    public void get(String url, BaseCallBack baseCallBack) {
        Request request = buildRequest(url, null, HttpMethodType.GET);
        sendRequest(request, baseCallBack);
    }


    /**
     * post请求
     *
     * @param url
     * @param params
     * @param baseCallBack
     */
    public void post(String url, HashMap<String, String> params, BaseCallBack baseCallBack) {
        Request request = buildRequest(url, params, HttpMethodType.POST);
        sendRequest(request, baseCallBack);

    }

    /**
     * 1）获取Request对象
     *
     * @param url
     * @param params
     * @param httpMethodType 请求方式不同，Request对象中的内容不一样
     * @return Request 必须要返回Request对象， 因为发送请求的时候要用到此参数
     */
    private Request buildRequest(String url, HashMap<String, String> params, HttpMethodType httpMethodType) {
        //获取辅助类对象
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        //如果是get请求
        if (httpMethodType == HttpMethodType.GET) {
            builder.get();
        } else {
            RequestBody body = buildFormData(params);
            builder.post(body);
        }

        //返回请求对象
        return builder.build();
    }

    /**
     * 2)发送网络请求
     *
     * @param request
     * @param baseCallBack
     */
    private void sendRequest(Request request, final BaseCallBack baseCallBack) {
        if (ConnUtils.isConnected(mActivity)) {
            //用于控制加载对象框的显示与隐藏
            //主线程
            baseCallBack.onRequestBefore(request);

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    callBackFail(baseCallBack, request, e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {

                        String json = response.body().string();

                        //此时请求结果在子线程里面，如何把结果回调到主线程里？
                        callBackSuccess(baseCallBack, response, json);
                    } else {
                        callBackError(baseCallBack, response, response.code());
                    }
                }
            });
        } else {
            //可以在子线程执行
            ToastUtils.showShort(mActivity, "您的网络不给力，请重试！");
        }
    }


    /**
     * 主要用于构建请求参数
     *
     * @param param
     * @return ResponseBody
     */
    private RequestBody buildFormData(HashMap<String, String> param) {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        //遍历HashMap集合
        if (param != null && !param.isEmpty()) {
            Set<Map.Entry<String, String>> entries = param.entrySet();
            for (Map.Entry<String, String> entity : entries) {
                String key = entity.getKey();
                String value = entity.getValue();
                builder.add(key, value);
            }
        }
        return builder.build();
    }

    //请求类型定义
    private enum HttpMethodType {
        GET,
        POST
    }

    //定义回调接口
    public interface BaseCallBack {
        //抽象方法里的参数是由调用处的方法参数决定的。(你想回调哪些参数会到activity？)
        void onRequestBefore(Request request);

        void onSuccess(Response response, String json);

        void onFail(Request request, IOException e);

        void onError(Response response, int code);
    }


    //主要用于子线程和主线程进行通讯
    private void callBackSuccess(final BaseCallBack baseCallBack, final Response response, final String json) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //相当于此run方法是在主线程执行的，可以进行更新UI的操作
                if (mActivity.isFinishing()) {
                    return;
                }
                baseCallBack.onSuccess(response, json);
            }
        });
    }


    private void callBackError(final BaseCallBack baseCallBack, final Response response, final int code) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mActivity.isFinishing()) {
                    return;
                }
                baseCallBack.onError(response, code);
            }
        });
    }

    private void callBackFail(final BaseCallBack baseCallBack, final Request request, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mActivity.isFinishing()) {
                    return;
                }
                //相当于此run方法是在主线程执行的，可以进行更新UI的操作
                baseCallBack.onFail(request, e);
            }
        });
    }

    private void callBackBefore(final BaseCallBack baseCallBack, final Request request) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mActivity.isFinishing()) {
                    return;
                }
                //相当于此run方法是在主线程执行的，可以进行更新UI的操作
                baseCallBack.onRequestBefore(request);
            }
        });
    }
}
