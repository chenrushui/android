package com.crs.demo.ui.enumcase;

/**
 * Created on 2016/10/8.
 * Author:crs
 * Description:路由跳转
 */
public enum FilterRouterActivityEnums {
    //枚举常量，常量名要大写，当前枚举类的子类
    SEARCH {
        public String getFormat() {
            return "/search";
        }
    },

    HOME {
        public String getFormat() {
            return "/home";
        }
    };
    //必须以分号结尾

    //在枚举类中定义一个抽象方法，让子类起实现，如果子类不实现，就会报错
    public abstract String getFormat();

    public boolean isMatch(String url) {
        //获取枚举数组
        FilterRouterActivityEnums[] enums = FilterRouterActivityEnums.values();
        //遍历枚举数组
        for (FilterRouterActivityEnums e : enums) {
            //子类调用其中的方法，返回字符串
            if (e.getFormat().equals(url)) {
                return true;
            }
        }
        return false;
    }
}
