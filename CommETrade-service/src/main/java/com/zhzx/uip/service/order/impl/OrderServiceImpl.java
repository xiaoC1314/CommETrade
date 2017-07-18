package com.zhzx.uip.service.order.impl;

import com.zhzx.uip.api.cust.model.OrderParam;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.exception.BusinessException;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.ResponseFactory;
import com.zhzx.uip.service.manager.order.OrderManager;
import com.zhzx.uip.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by fu on 2017/7/18.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderManager orderManager;

    @Override
    public ResponseVo createOrder(OrderParam param) {
        String orderNo;
        try {
            orderNo = orderManager.createOrder(param);
        }catch (BusinessException e){
            logger.error("用户["+param.getCustNo()+"]创建订单失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_CART_IS_NULL_ERR);
        } catch (Exception e) {
            logger.error("用户["+param.getCustNo()+"]创建订单失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(orderNo);
    }
}
