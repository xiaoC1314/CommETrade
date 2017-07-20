package com.zhzx.uip.service.order;

import com.zhzx.uip.api.cust.model.OrderParam;
import com.zhzx.uip.commons.module.ResponseVo;

/**
 * Created by fu on 2017/7/18.
 */
public interface OrderService {


    /**
     * 创建订单
     * @param param
     * @return
     */
    ResponseVo createOrder(OrderParam param);


    /**
     * 查询订单详情
     * @param orderNo
     * @return
     */
    ResponseVo queryOrder(String orderNo);


    /**
     * 查询订单列表
     * @param custNo
     * @param page
     * @param pageSize
     * @return
     */
    ResponseVo queryOrderList(String custNo,Integer page,Integer pageSize);

    ResponseVo paid(String orderNo);

    ResponseVo revoke(String orderNo);

    ResponseVo finish(String orderNo);

    ResponseVo applyForReturn(String orderNo);
}
