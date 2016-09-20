package com.crs.demo.ui.retrofit2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.constant.UrlConstant;
import com.crs.demo.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 2016/9/20.
 * Author:crs
 * Description:网络请求框架Retrofit2的使用
 */
public class Retrofit2Activity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = Retrofit2Activity.class.getSimpleName();
    private TextView tv_retrofit;
    private Button btn_retrofit_get;
    private Button btn_retrofit_download;
    private Button btn_retrofit_test;
    private Button btn_retrofit_form;
    private Call<ResponseBody> call_test;
    private Call<RetrofitOrderEntity> call_get;
    private Call<ResponseBody> call_form;
    private Call<ResponseBody> call_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);

        initViews();
        initListener();


    }


    private void initViews() {
        tv_retrofit = findView(R.id.tv_retrofit);
        btn_retrofit_get = findView(R.id.btn_retrofit_get);
        btn_retrofit_download = findView(R.id.btn_retrofit_download);
        btn_retrofit_test = findView(R.id.btn_retrofit_test);
        btn_retrofit_form = findView(R.id.btn_retrofit_form);
    }

    private void initListener() {
        btn_retrofit_get.setOnClickListener(this);
        btn_retrofit_download.setOnClickListener(this);
        btn_retrofit_test.setOnClickListener(this);
        btn_retrofit_form.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retrofit_get: {
                clickGet();
            }
            break;
            case R.id.btn_retrofit_download: {
                clickDownload();
            }
            break;
            case R.id.btn_retrofit_test: {
                clickTest();
            }
            break;
            case R.id.btn_retrofit_form: {
                clickForm();
            }
            break;
        }

    }

    private void clickTest() {
        //1)创建Retrofit对象
        /**
         * 1）建议：baseUrl以/结尾，而请求方式后面的()中的URL字符串不以/开头。
         * 2）new Retrofit.xxx
         */
        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlConstant.BASE_API).build();
        //2)获取接口服务对象
        /**
         * 1)调用create(类的字节码文件)方法
         */
        MyServiceInterface myServiceInterface = retrofit.create(MyServiceInterface.class);
        //3)获取Call对象
        call_test = myServiceInterface.getOrderStatus();
        //4)call对象执行异步请求
        /**
         * 1)回调结果在主线程里，可以直接更新UI。
         * 2)按照OKHttp的形式发送网络请求，进行处理服务器端返回的Response对象。
         */
        call_test.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //如果服务器端响应不为空
                if (response.isSuccessful()) {
                    try {
                        //获取服务器端返回的json字符串
                        String json = response.body().string();
                        tv_retrofit.setText(json);

                        //查看请求的结果到底是子线程还是主线程？
                        //获取当前线程
                        Thread thread = Thread.currentThread();
                        //获取当前线程的id，如果 线程id为1，表示是主线程里面，即网络请求结果在主线程里面,
                        //所以我们可以放心大胆的在里面更新UI
                        long id = thread.getId();
                        LogUtils.i(TAG, id + "");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void clickGet() {
        // 这行代码的作用是：让Retrofit2自动帮我们解析json字符串
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyServiceInterface myServiceInterface = retrofit.create(MyServiceInterface.class);
        //第一个为路径参数、第二个为查询参数
        call_get = myServiceInterface.getOrderStatusData("GetOrderStatusForCheLun", "TH01587458");
        call_get.enqueue(new Callback<RetrofitOrderEntity>() {
            @Override
            public void onResponse(Call<RetrofitOrderEntity> call, Response<RetrofitOrderEntity> response) {
                if (response != null && response.isSuccessful()) {
                    RetrofitOrderEntity body = response.body();
                    if (body != null) {
                        String status = body.getStatus();
                        if (!TextUtils.isEmpty(status)) {
                            tv_retrofit.setText(status);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RetrofitOrderEntity> call, Throwable t) {

            }
        });
    }

    private void clickDownload() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyServiceInterface myServiceInterface = retrofit.create(MyServiceInterface.class);
        call_download = myServiceInterface.getDownloadImage();
        call_download.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        byte[] bytes = response.body().bytes();
                        //把数组解析成Bitmap对象
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private void clickForm() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlConstant.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyServiceInterface myServiceInterface = retrofit.create(MyServiceInterface.class);
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", "d39a6b5d-20a1-f996-2f7d-66087d317152");
        params.put("province", "北京市");
        params.put("city", "石景山区");
        call_form = myServiceInterface.getCityData(params);
        call_form.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String json = response.body().string();
                        //可以请求成功
                        tv_retrofit.setText(json);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }


        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        //取消网络请求
        call_get.cancel();
        call_test.cancel();
        call_form.cancel();
    }
}
