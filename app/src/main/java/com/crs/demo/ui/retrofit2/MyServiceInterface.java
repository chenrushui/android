package com.crs.demo.ui.retrofit2;


import com.crs.demo.constant.UrlConstant;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created on 2016/9/20.
 * Author:crs
 * Description:MyServiceInterface
 */
public interface MyServiceInterface {
    //要了解每个注释的含义，其实每个注解都是接口，里面有一些抽象方法
    //返回值类型为ResponseBody
    //@GET() get请求方式，()中的内容为url
    //泛型表示返回的数据模型
    //@QueryMap()注解
    //路径参数 查询参数


    @GET(UrlConstant.ORDER_STATUS)
    Call<ResponseBody> getOrderStatus();

    //查询数据
    @GET("Order/{type}?")
    Call<RetrofitOrderEntity> getOrderStatusData(@Path("type") String url,
                                                 @Query("orderNo") String orderNo);

    //get方式下载图片
    @GET(UrlConstant.IMAGE_URL)
    Call<ResponseBody> getDownloadImage();

    //get方式提交表单数据
    @GET(UrlConstant.GET_CITY_DATA)
    Call<ResponseBody> getCityData(@QueryMap HashMap<String, String> params);


}
