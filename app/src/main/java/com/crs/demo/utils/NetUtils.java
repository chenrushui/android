package com.crs.demo.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.crs.demo.base.BaseApplication;
import com.crs.demo.bean.ResponseEntity;
import com.crs.demo.constant.UrlConstant;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:基于AFinal网络请求的封装
 */
public class NetUtils extends AjaxCallBack<Object> {
    private boolean isPost = false;
    private boolean isShow = false;
    private boolean isFull = false;
    private boolean isShowMessage = true;
    private Dialog mDialog;
    private Context context;
    private FinalHttp http;
    private ResponseCallBack responseCallBack;

    public NetUtils(Context context) {
        this.context = context;
        http = new FinalHttp();
    }

    //1)判断是不是post网络请求
    public void isPost(boolean isPost) {
        this.isPost = isPost;
    }

    //2)判断是不是完整的url 因为有不同的站点:test、api、线上正式站点
    public void isFullUrl(boolean isFull) {
        this.isFull = isFull;
    }

    //3)判断是否显示请求结果信息
    public void isShowMsg(boolean isShowMessage) {
        this.isShowMessage = isShowMessage;
    }


    //4)是否显示网络数据加载进度，如何控制dialog的显示与隐藏。
    public void isShowDialog(boolean isShow) {
        this.isShow = isShow;
        mDialog = DialogUtils.createLoadingDialog(context);
    }


    //设置网络请求结果回调
    public interface ResponseCallBack {
        void getResponseData(ResponseEntity response);
    }


    //网络请求生命周期方法
    @Override
    public void onStart() {
        super.onStart();
        if (isShow && !((Activity) context).isFinishing()) {
            if (mDialog != null && !mDialog.isShowing()) {
                mDialog.show();
            } else {
                mDialog = DialogUtils.createLoadingDialog(context);
                mDialog.show();
            }
        }
    }

    @Override
    public void onSuccess(Object t) {
        super.onSuccess(t);
        if (mDialog != null && isShow && mDialog.isShowing()) {
            mDialog.cancel();
            mDialog = null;
        }

        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        //解析返回的结果数据
        if (t != null) {
            ResponseEntity response = new ResponseEntity(t);
            response.parse();
            if (responseCallBack != null) {
                responseCallBack.getResponseData(response);
            }
        } else {
            if (responseCallBack != null) {
                responseCallBack.getResponseData(null);
            }
        }
    }

    @Override
    public void onFailure(Throwable t, int errorNo, String strMsg) {
        super.onFailure(t, errorNo, strMsg);
        if (mDialog != null && isShow && mDialog.isShowing()) {
            mDialog.cancel();
            mDialog = null;
        }

        if (isShowMessage) {
            ToastUtils.showLong(context, "您的网络不给力，请重试！");
        }

        if (responseCallBack != null) {
            responseCallBack.getResponseData(null);
        }
    }

    //访问api站点
    public void sendToApi(HashMap<String, String> params, String url, final ResponseCallBack responseCallBack) {
        if (ConnUtils.isConnected(context)) {
            AjaxParams ajaxParams = transformHashMap(params);
            if (!isFull) {
                url = UrlConstant.BASE_API + url;
            }
            if (isPost) {
                http.post(url, ajaxParams, this);
                //http.post(url, ajaxParams, new AjaxCallBack<ResponseEntity>() {
                //重写父类中的方法
                // @Override
                //public void onSuccess(ResponseEntity responseEntity) {
                // super.onSuccess(responseEntity);
                //responseCallBack.getResponseData(responseEntity);
                // }
                // });

            } else {
                http.post(url, ajaxParams, this);
                //http.get(url, ajaxParams, new AjaxCallBack<ResponseEntity>() {
                // @Override
                // public void onSuccess(ResponseEntity responseEntity) {
                //   super.onSuccess(responseEntity);
                //    responseCallBack.getResponseData(responseEntity);
                // }
                // });

            }
        } else {
            //如果网络没有连接
            ToastUtils.showLong(context, "网络连接不可用,是否进行设置?");

        }
    }

    //访问test站点
    public void sendToTest(HashMap<String, String> params, String url, final ResponseCallBack responseCallBack) {
        if (ConnUtils.isConnected(context)) {
            AjaxParams ajaxParams = transformHashMap(params);
            //请求头都是一些公共的信息
            //http.addHeader("usersession", PreferenceUtil.getString(this.context, "usersession", null, "tuhu_table"));
            http.addHeader("VersionNumber", VersionUtils.getVersionName(context));
            http.addHeader("VersionCode", VersionUtils.getVersionCode(context) + "");
            http.addHeader("ChannelType", "android");
            http.addHeader("Source", BaseApplication.getInstance().getChannelId());//渠道来源
            http.addHeader("DeviceID", BaseApplication.getInstance().getUUID());
            //http.addHeader("black_box", FMAgent.onEvent(context));//此处填写移动端sdk采集到的信息black_box

            if (!isFull) {
                url = UrlConstant.BASE_API + url;
            }
            if (isPost) {
                http.post(url, ajaxParams, this);
            } else {
                http.post(url, ajaxParams, this);

            }
        } else {
            //如果网络没有连接
            ToastUtils.showLong(context, "网络连接不可用,是否进行设置?");

        }
    }

    //访问dev站点
    public void sendToDev(HashMap<String, String> params, String url, final ResponseCallBack responseCallBack) {
        if (ConnUtils.isConnected(context)) {
            AjaxParams ajaxParams = transformHashMap(params);
            if (!isFull) {
                url = UrlConstant.BASE_API + url;
            }
            if (isPost) {
                http.post(url, ajaxParams, this);
            } else {
                http.post(url, ajaxParams, this);
            }
        } else {
            //如果网络没有连接
            ToastUtils.showLong(context, "网络连接不可用,是否进行设置?");

        }
    }

    private AjaxParams transformHashMap(HashMap<String, String> params) {
        AjaxParams param = new AjaxParams();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null) {
                param.put(key, value);
            }
        }
        return param;
    }

}
