package com.zhzx.uip.service.order;

import com.zhzx.uip.api.cust.model.OrderParam;
import com.zhzx.uip.commons.module.ResponseVo;

/**
 * Created by fu on 2017/7/18.
 */
public interface OrderService {


    ResponseVo createOrder(OrderParam param);
}
