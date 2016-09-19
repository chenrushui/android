package com.crs.demo.ui.loginmvp.presenter;

import com.crs.demo.ui.loginmvp.model.ILoginModel;
import com.crs.demo.ui.loginmvp.model.LoginModelImpl;
import com.crs.demo.ui.loginmvp.view.ILoginView;

/**
 * Created on 2016/9/14.
 * Author:crs
 * Description:presenter层的业务逻辑
 */
public class LoginPresenter implements ILoginPresenter {
    //Presenter必须要持有两个接口的引用
    private ILoginView iLoginView;
    private ILoginModel loginModel;

    //通过构造方法，让presenter类持有model和view两个接口的引用
    public LoginPresenter(ILoginView view) {
        //如果这样能够传递，则Activity必须实现  IUserView接口，重写其中的抽象方法
        this.iLoginView = view;
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
