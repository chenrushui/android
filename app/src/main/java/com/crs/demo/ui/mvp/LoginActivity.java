package com.crs.demo.ui.mvp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.ui.mvp.presenter.LoginPresenter;
import com.crs.demo.ui.mvp.view.ILoginView;
import com.crs.demo.utils.ToastUtils;

/**
 * Created on 2016/9/14.
 * Author:crs
 * Description:使用mvp模式写登录模块
 * 1)这样就需要按模块分包，不能使用mvc模式分包了
 * 2)当前类如何持有presenter类的引用，直接创建对象！
 * 3)怎样抽取presenter类的方法，按照当前activity业务逻辑抽取方法
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    private EditText et_user_name;
    private EditText et_password;
    private Button btn_login;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        intListener();

        loginPresenter = new LoginPresenter(this);

    }

    private void intListener() {
        btn_login.setOnClickListener(this);
    }

    private void initViews() {
        et_user_name = findView(R.id.et_user_name);
        et_password = findView(R.id.et_password);
        btn_login = findView(R.id.btn_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                clickLogin();
            }
            break;
        }
    }

    private void clickLogin() {
        String userName = et_user_name.getText().toString();
        String password = et_password.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showLong(LoginActivity.this, "请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showLong(LoginActivity.this, "请输入密码");
            return;
        }

        //传递数据
        loginPresenter.login(userName, password);
    }


    @Override
    public void loginSuccess(String success) {
        ToastUtils.showLong(LoginActivity.this, success);

    }

    @Override
    public void loginFail(String fail) {
        ToastUtils.showLong(LoginActivity.this, fail);
    }
}
