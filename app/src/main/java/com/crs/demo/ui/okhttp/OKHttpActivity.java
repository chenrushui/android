package com.crs.demo.ui.okhttp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.constant.UrlConstant;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created on 2016/9/19.
 * Author:crs
 * Description:OKHttp网络请求框架的使用
 */
public class OKHttpActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_ok_http_get;
    private Button btn_ok_http_post;
    private TextView tv_ok_http_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        initViews();
        initListener();

    }

    private void initViews() {
        btn_ok_http_get = findView(R.id.btn_ok_http_get);
        btn_ok_http_post = findView(R.id.btn_ok_http_post);
        tv_ok_http_content = findView(R.id.tv_ok_http_content);
    }

    private void initListener() {
        btn_ok_http_get.setOnClickListener(this);
        btn_ok_http_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok_http_get: {
                clickGet();
            }
            break;
            case R.id.btn_ok_http_post: {
                clickPost();
            }
            break;
        }
    }

    private void clickPost() {
        HttpUtils httpUtils = new HttpUtils(OKHttpActivity.this);
        HashMap<String, String> params = new HashMap<>();
        params.put("orderNo", "TH01587458");
        httpUtils.post(UrlConstant.ORDER_STATUS_POST, params, new HttpUtils.BaseCallBack() {
            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onSuccess(Response response, String json) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(json);
                    String status = jsonObject.getString("Status");
                    tv_ok_http_content.setText(status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(Request request, IOException e) {

            }

            @Override
            public void onError(Response response, int code) {

            }
        });
    }

    private void clickGet() {
        HttpUtils httpUtils = new HttpUtils(OKHttpActivity.this);
        httpUtils.get(UrlConstant.ORDER_STATUS, new HttpUtils.BaseCallBack() {
            @Override
            public void onRequestBefore(Request request) {
                //加载网络数据开始前，所做的操作
            }

            @Override
            public void onSuccess(Response response, String json) {
                //使用json包解析  注意注解的使用
                Gson gson = new Gson();
                {
                    Entity entity = gson.fromJson(json, Entity.class);
                    AfterSaleType afterSaleType = entity.getAfterSaleType();
                    String shopService = afterSaleType.getShopService();
                    tv_ok_http_content.setText(shopService);
                }

                //只能使用内部类的形式去封装对象，这样就不会报错
                {
                    //ResponseEntity responseEntity = gson.fromJson(json, ResponseEntity.class);
                    //ResponseEntity.AfterSaleType afterSaleType = responseEntity.getAfterSaleType();
                    //String shopService = afterSaleType.getShopServiceTousu();
                    //tv_ok_http_content.setText(shopService);
                }
            }

            @Override
            public void onFail(Request request, IOException e) {


            }

            @Override
            public void onError(Response response, int code) {

            }
        });
    }

}
