package com.crs.demo.bean;

import com.crs.demo.constant.IntConstant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:统一的返回response
 */
public class ResponseEntity {

    private Object response;
    private JSONObject jsonObject;

    public ResponseEntity(Object t) {
        response = t;
    }

    public Boolean isSuccessful() {
        return getCode() == IntConstant.RESPONSE_CODE_SUCCESS;
    }

    //获取响应码
    private int getCode() {
        if (jsonObject == null) {
            return IntConstant.RESPONSE_GET_NULL;
        }
        if (jsonObject.has("Code")) {
            try {
                return jsonObject.getInt("Code");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return IntConstant.RESPONSE_GET_NULL;
    }

    //把json字符串解析成json对象
    public JSONObject parse() {
        if (response == null) {
            return null;
        }
        try {
            jsonObject = new JSONObject(response.toString());
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
