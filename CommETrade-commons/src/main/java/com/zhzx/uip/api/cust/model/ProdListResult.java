package com.zhzx.uip.api.cust.model;

/**
 * Created by fu on 2017/7/20.
 */
public class ProdListResult {

    /**
     *订单编号
     */
    private String orderNo;

    /**
     *客户编号
     */
    private String custNo;

    /**
     *产品编号
     */
    private String prodNo;

    /**
     *产品名称
     */
    private String prodName;

    /**
     *产品价格
     */
    private String prodPrice;

    /**
     *产品数量
     */
    private Integer prodNum;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Integer getProdNum() {
        return prodNum;
    }

    public void setProdNum(Integer prodNum) {
        this.prodNum = prodNum;
    }
}
