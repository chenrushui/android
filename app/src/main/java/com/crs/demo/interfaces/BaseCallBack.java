package com.crs.demo.interfaces;

import com.google.gson.internal.$Gson$Types;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created on 2016/8/30.
 * Author:crs
 * Description:用于执行网络请求结果回调
 * <T> 表示返回的对象类型
 */
public abstract class BaseCallBack<T> {
    //如何把T转化成type
    public Type type = null;

    public BaseCallBack() {
        this.type = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<? extends BaseCallBack> aClass) {
        Type superclass = aClass.getGenericSuperclass();
        if (superclass instanceof Class) {
            //不进行处理

        }
        ParameterizedType param = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(param.getActualTypeArguments()[0]);
    }


    public abstract void onRequestBefore(Request request);

    public abstract void onFailure(Request request, Exception e);

    public abstract void onSuccess(Response response, T t);

    public abstract void onError(Response response, int code, Exception e);

}
