package com.crs.demo.bean;

/**
 * Created on 2016/8/31.
 * Author:crs
 * Description:订单状态
 */
public class OrderStatusEntity {

    /**
     * Code : 1
     * AfterSaleType : {"UnReceive":"未收到货","ReturnGoods":"退货","ShopServiceTousu":"门店服务投诉","ProductQuality":"货品质量问题","Invoice":"发票问题","Other":"其他"}
     */

    private String Code;
    private AfterSaleTypeEntity AfterSaleType;

    public void setCode(String Code) {
        this.Code = Code;
    }

    public void setAfterSaleType(AfterSaleTypeEntity AfterSaleType) {
        this.AfterSaleType = AfterSaleType;
    }

    public String getCode() {
        return Code;
    }

    public AfterSaleTypeEntity getAfterSaleType() {
        return AfterSaleType;
    }

    public static class AfterSaleTypeEntity {
        /**
         * UnReceive : 未收到货
         * ReturnGoods : 退货
         * ShopServiceTousu : 门店服务投诉
         * ProductQuality : 货品质量问题
         * Invoice : 发票问题
         * Other : 其他
         */

        private String UnReceive;
        private String ReturnGoods;
        private String ShopServiceTousu;
        private String ProductQuality;
        private String Invoice;
        private String Other;

        public void setUnReceive(String UnReceive) {
            this.UnReceive = UnReceive;
        }

        public void setReturnGoods(String ReturnGoods) {
            this.ReturnGoods = ReturnGoods;
        }

        public void setShopServiceTousu(String ShopServiceTousu) {
            this.ShopServiceTousu = ShopServiceTousu;
        }

        public void setProductQuality(String ProductQuality) {
            this.ProductQuality = ProductQuality;
        }

        public void setInvoice(String Invoice) {
            this.Invoice = Invoice;
        }

        public void setOther(String Other) {
            this.Other = Other;
        }

        public String getUnReceive() {
            return UnReceive;
        }

        public String getReturnGoods() {
            return ReturnGoods;
        }

        public String getShopServiceTousu() {
            return ShopServiceTousu;
        }

        public String getProductQuality() {
            return ProductQuality;
        }

        public String getInvoice() {
            return Invoice;
        }

        public String getOther() {
            return Other;
        }
    }
}
