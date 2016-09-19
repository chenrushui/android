package com.crs.demo.ui.okhttp;

/**
 * Created on 2016/9/19.
 * Author:crs
 * Description:ResponseEntity
 */
public class ResponseEntity {
    private String Code;
    private AfterSaleType AfterSaleType;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public ResponseEntity.AfterSaleType getAfterSaleType() {
        return AfterSaleType;
    }

    public void setAfterSaleType(ResponseEntity.AfterSaleType afterSaleType) {
        AfterSaleType = afterSaleType;
    }

    public class AfterSaleType {
        private String UnReceive;
        private String ReturnGoods;
        private String ShopServiceTousu;
        private String ProductQuality;
        private String Invoice;
        private String Other;

        public String getUnReceive() {
            return UnReceive;
        }

        public void setUnReceive(String unReceive) {
            UnReceive = unReceive;
        }

        public String getReturnGoods() {
            return ReturnGoods;
        }

        public void setReturnGoods(String returnGoods) {
            ReturnGoods = returnGoods;
        }

        public String getShopServiceTousu() {
            return ShopServiceTousu;
        }

        public void setShopServiceTousu(String shopServiceTousu) {
            ShopServiceTousu = shopServiceTousu;
        }

        public String getProductQuality() {
            return ProductQuality;
        }

        public void setProductQuality(String productQuality) {
            ProductQuality = productQuality;
        }

        public String getInvoice() {
            return Invoice;
        }

        public void setInvoice(String invoice) {
            Invoice = invoice;
        }

        public String getOther() {
            return Other;
        }

        public void setOther(String other) {
            Other = other;
        }
    }

}
