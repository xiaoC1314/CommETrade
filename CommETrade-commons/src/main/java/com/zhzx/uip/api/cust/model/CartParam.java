package com.zhzx.uip.api.cust.model;

/**
 * Created by fu on 2017/7/18.
 */
public class CartParam {


    /**产品编号*/
    private String prodNo;

    /**产品数量*/
    private Integer num;

    /**客户编号*/
    private String custNo;


    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    @Override
    public String toString() {
        return "CartParam{" +
                "prodNo='" + prodNo + '\'' +
                ", num=" + num +
                ", custNo='" + custNo + '\'' +
                '}';
    }
}
