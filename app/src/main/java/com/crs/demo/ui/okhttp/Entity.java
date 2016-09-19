package com.crs.demo.ui.okhttp;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 2016/9/19.
 * Author:crs
 * Description:请求结果实体模型
 */
public class Entity {
    @SerializedName("Code")
    private String code;
    @SerializedName("AfterSaleType")
    private AfterSaleType afterSaleType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AfterSaleType getAfterSaleType() {
        return afterSaleType;
    }

    public void setAfterSaleType(AfterSaleType afterSaleType) {
        this.afterSaleType = afterSaleType;
    }
}
