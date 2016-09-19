package com.crs.demo.ui.loginmvp.model;

import com.crs.demo.ui.loginmvp.presenter.ILoginPresenter;

/**
 * Created on 2016/9/14.
 * Author:crs
 * Description:进行网络数据的请求,处理具体的业务逻辑
 */
public class LoginModelImpl implements ILoginModel {

    ILoginPresenter iLoginPresenter;

    public LoginModelImpl(ILoginPresenter iLoginPresenter) {
        this.iLoginPresenter = iLoginPresenter;
    }

    @Override
    public void getLoginStatus(String userName, String password) {
        //获取网络数据操作，bean
        if (iLoginPresenter != null) {
            //业务逻辑处理完毕,进行结果的接口回调
            if (userName.equals("1111") && password.equals("1111")) {
                iLoginPresenter.loginSuccess("登录成功！");
            } else {
                iLoginPresenter.loginFail("登录失败！");
            }
        }
    }
}
