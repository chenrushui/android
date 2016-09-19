package com.crs.demo.ui.rxandroid;

import rx.Observable;
import rx.Subscriber;

/**
 * Created on 2016/9/18.
 * Author:crs
 * Description:RxUtils
 */
public class RxUtils {

    //获取当前类的名称
    private static String TAG = RxUtils.class.getSimpleName();


    /**
     * 1)提供一个方法，去去创建被观察者.创建被观察者
     * 2)注意是.rx包下的文件
     * 3)泛型表示要传递的数据类型，表示被观察者返回的数据类型
     * 4)Observable有很多操作符，用于处理不同的数据类型 filter过滤功能
     */
    public static void createObserable() {

        //创建一个被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //如果观察者和被观察者有订阅关系
                if (!subscriber.isUnsubscribed()) {
                    //被观察者向观察者传递数据,可以在onNext中调用方法，结果是按照顺序输出的
                    subscriber.onNext("1)碧云天，黄叶地，秋色连波，波上寒烟翠！");
                    subscriber.onNext("2)山映斜阳天接水，芳草无情，更在斜阳外！");
                    subscriber.onNext("3)暗销魂，追旅思，夜夜除非，好梦留人睡！");
                    subscriber.onNext("4)明月高楼休独倚，酒入愁肠，化作相思泪！");
                    subscriber.onNext(getData());
                    //表示完成数据的发送
                    subscriber.onCompleted();
                }
            }
        });

        //创建一个订阅对象
        Subscriber<String> showSub = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            //2)表示遇到错误
            @Override
            public void onError(Throwable e) {

            }

            //1)观察者调用此方法用于发射数据
            @Override
            public void onNext(String s) {
                //s

            }
        };

        //订阅对象如何关联被观察者
        //此行代码可以使用链式编程来实现
        observable.subscribe(showSub);

    }

    private static String getData() {
        return "{code :1 ,name:陈如水}";
    }
}
