package com.crs.demo.ui.retrofit2;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 2016/9/20.
 * Author:crs
 * Description:RetrofitOrderEntity 实体模型
 */
public class RetrofitOrderEntity {
    //{"Code":"1","Status":"2Shipped"}

    @SerializedName("Code")
    private String code;
    @SerializedName("Status")
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetrofitOrderEntity{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
