package com.crs.demo.ui.rx;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created on 2016/9/19.
 * Author:crs
 * Description:查询工具类
 */
public class QueryUtils {

    private OkHttpClient httpUtils;

    public QueryUtils() {
        httpUtils = new OkHttpClient();
    }

    /**
     * 定义查询操作，使用rx的编程思想
     *
     * @param url
     * @param orderId
     * @return
     */
    public Observable<String> queryOrder(final String url, final String orderId) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    RequestBody body = new FormEncodingBuilder().add("orderNo", orderId).build();
                    Request request = new Request.Builder().url(url).post(body).build();
                    httpUtils.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String str = response.body().string();
                                subscriber.onNext(str);
                            }
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });
    }

}
