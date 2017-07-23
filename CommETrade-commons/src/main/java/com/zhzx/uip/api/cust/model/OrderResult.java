package com.zhzx.uip.api.cust.model;

import java.util.List;

/**
 * Created by fu on 2017/7/20.
 */
public class OrderResult {

    /**
     *订单编号
     */
    private String id;

    /**
     *客户编号
     */
    private String custNo;

    /**
     *地址编号
     */
    private String addNo;

    /**
     *状态（1:生成订单，2：等待发货，3：完成，-1:订单取消，-2：订单过期）
     */
    private String status;

    /**
     * 订单收货地址信息
     */
    private AddressResult addressResult;

    /**
     * 产品清单
     */
    private List<ProdListResult> prodList;

    /**
     * 创建时间
     */
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProdListResult> getProdList() {
        return prodList;
    }

    public void setProdList(List<ProdListResult> prodList) {
        this.prodList = prodList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public AddressResult getAddressResult() {
        return addressResult;
    }

    public void setAddressResult(AddressResult addressResult) {
        this.addressResult = addressResult;
    }
}
