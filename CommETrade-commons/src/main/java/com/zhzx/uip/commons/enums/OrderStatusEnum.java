package com.zhzx.uip.commons.enums;

/**
 * Created by fu on 2017/7/20.
 */
public enum OrderStatusEnum {

    INIT("1","生成订单"),
    PAID("2","支付完成"),
    DELIVERING("3","发货中"),
    DELIVERY_SUCCESS("4","送货完成"),
    FINISHED("5","订单完成"),
    COMMENTED("6","评价完成"),
    REVOKED("-1","订单取消"),
    EXPIRED("-2","订单过期"),
    RETURNING("-3","退货中"),
    RETURNED("-4","退货完成");

    private String status;

    private String msg;

    OrderStatusEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static void main(String[] args) {
        System.out.println(INIT.getStatus());
    }
}
