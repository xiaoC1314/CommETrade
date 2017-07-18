package com.zhzx.uip.api.cust.model;

/**
 * Created by fu on 2017/7/17.
 */
public class OrderParam {


    /**客户编号*/
    private String custNo;

    /**地址编号*/
    private String addNo;

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getAddNo() {
        return addNo;
    }

    public void setAddNo(String addNo) {
        this.addNo = addNo;
    }

    @Override
    public String toString() {
        return "OrderParam{" +
                "custNo=" + custNo +
                ", addNo=" + addNo +
                '}';
    }
}
