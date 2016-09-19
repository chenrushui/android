package com.crs.demo.ui.rx;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created on 2016/9/19.
 * Author:crs
 * Description:下载工具类
 */
public class DownloadUtils {

    private OkHttpClient httpUtils;

    public DownloadUtils() {
        httpUtils = new OkHttpClient();
    }

    /**
     * 定义下载方法，使用rx的编程思想
     *
     * @param url
     * @return
     */
    public Observable<byte[]> downloadImage(final String url) {
        //创建被观察者
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                //判断观察者和被观察者是否存在订阅关系
                if (!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder().url(url).build();
                    httpUtils.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            if (response.isSuccessful()) {
                                //拿到结果的一瞬间触发事件，并传递数据给观察者
                                //把请求结果转化成字节数组
                                byte[] bytes = response.body().bytes();
                                subscriber.onNext(bytes);
                            }
                            //数据发送已经完成
                            subscriber.onCompleted();
                        }
                    });


                }
            }
        });

    }


}
