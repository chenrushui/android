package com.crs.demo.constant;

/**
 * Created on 2016/8/19.
 * Author:crs
 * Description:url常量类
 */
public interface UrlConstant {

    String BASE_API = "http://api.tuhu.test/";

    String ORDER_STATUS = "Order/GetTousuTypeValue";
    String ORDER_STATUS_POST = "Order/GetOrderStatusForCheLun";
    String GET_CITY_DATA = "Addresses/SelectAddresses";
    String IMAGE_URL = "http://www.fjsen.com/images/attachement/jpg/site2/20111027/001aa02cbc3b1012e7b936.jpg";

    String ORDER_STATUS_POST_DETAILS = "http://api.tuhu.test/Order/GetOrderStatusForCheLun?orderNo=TH01587458";
    String GET_CITY_DATA_DETAILS = "http://api.tuhu.test/Addresses/SelectAddresses?userId={d39a6b5d-20a1-f996-2f7d-66087d317152}&province=北京市&city=石景山区";
}
