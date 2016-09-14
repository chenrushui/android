package com.crs.demo.ui.login.presenter;

import com.crs.demo.ui.login.model.ILoginModel;
import com.crs.demo.ui.login.model.LoginModelImpl;
import com.crs.demo.ui.login.view.ILoginView;

/**
 * Created on 2016/9/14.
 * Author:crs
 * Description:presenter层的业务逻辑
 */
public class LoginPresenter implements ILoginPresenter {
    private ILoginView iLoginView;
    private ILoginModel loginModel;

    //通过构造方法，让presenter类持有model和view两个接口的引用
    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModel = new LoginModelImpl(this);
    }

    //根据业务逻辑定义的方法
    public void login(String userName, String password) {
        loginModel.getLoginStatus(userName, password);
    }

    @Override
    public void loginSuccess(String success) {
        iLoginView.loginSuccess(success);

    }

    @Override
    public void loginFail(String fail) {
        iLoginView.loginFail(fail);
    }
}
