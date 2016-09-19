package com.crs.demo.ui.loginmvp.view;

/**
 * Created on 2016/9/14.
 * Author:crs
 * Description:界面监听接口 登录结果回调到activity中
 */
public interface ILoginView {
    void loginSuccess(String success);
    void loginFail(String fail);

}
