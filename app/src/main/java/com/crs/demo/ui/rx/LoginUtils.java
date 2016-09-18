package com.crs.demo.ui.rx;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import rx.Observable;
import rx.Subscriber;

/**
 * Created on 2016/9/18.
 * Author:crs
 * Description:登录工具类
 */
public class LoginUtils {
    private static OkHttpClient httpUtils;

    public LoginUtils() {
        httpUtils = new OkHttpClient();
    }


    /**
     * 定义login操作，使用RxAndroid的编程思想
     *
     * @param url
     * @param login
     * @param password
     * @return 定义字符串类型的返回值
     */
    public Observable<String> loginPost(final String url, final String login, final String password) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    RequestBody formBody = new FormEncodingBuilder().add("name", login).add("password", password).build();
                    Request request = new Request.Builder().url(url).post(formBody).build();
                    httpUtils.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            if (response.isSuccessful()) {
                                subscriber.onNext(response.body().string());
                            }
                            subscriber.onCompleted();
                        }
                    });

                }
            }
        });
    }
}
