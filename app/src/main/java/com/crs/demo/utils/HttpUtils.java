package com.crs.demo.utils;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created on 2016/8/19.
 * Author:crs
 * Description:封装网络请求
 */
public class HttpUtils extends OkHttpClient {
    private OkHttpClient okHttp;

    public HttpUtils() {
        okHttp = new OkHttpClient();
    }
    public void send() {
        final Request request = new Request.Builder().url("https://github.com/hongyangAndroid").build();
        Call call = okHttp.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
            }
        });
    }
}
