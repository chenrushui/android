package com.crs.demo.ui.rx;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created on 2016/9/18.
 * Author:crs
 * Description:下载工具类
 */
public class DownLoadUtils {

    private OkHttpClient httpUtils;

    public DownLoadUtils() {
        this.httpUtils = new OkHttpClient();
    }

    //返回值为字节数组

    /**
     * 声明一个被观察者对象，并作为结果返回
     *
     * @param url
     * @return
     */
    public Observable<byte[]> downloadImage(final String url) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    //进行所谓的下载操作,访问网络
                    Request request = new Request.Builder().url(url).build();
                    //进行请求结果的回调,已经拿到响应结果
                    httpUtils.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            if (response.isSuccessful()) {
                                //把响应结果转化成字节数组
                                byte[] bytes = response.body().bytes();
                                if (bytes != null) {
                                    subscriber.onNext(bytes);
                                }
                            }
                            //表示数据传递已经完成
                            subscriber.onCompleted();
                        }
                    });
                }

            }
        });
    }
}
