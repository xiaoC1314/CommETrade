package com.zhzx.uip.service.order.impl;

import com.google.common.collect.Lists;
import com.zhzx.dao.bean.order.OrderInfo;
import com.zhzx.dao.bean.order.ProdList;
import com.zhzx.dao.service.order.OrderInfoService;
import com.zhzx.uip.api.cust.model.OrderParam;
import com.zhzx.uip.api.cust.model.OrderResult;
import com.zhzx.uip.api.cust.model.ProdListResult;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.enums.OrderStatusEnum;
import com.zhzx.uip.commons.exception.BusinessException;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.ResponseFactory;
import com.zhzx.uip.service.manager.order.OrderManager;
import com.zhzx.uip.service.manager.order.ProdListManager;
import com.zhzx.uip.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by fu on 2017/7/18.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderManager orderManager;

    @Autowired
    ProdListManager prodListManager;

    @Autowired
    OrderInfoService orderInfoService;


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

    @Override
    public ResponseVo queryOrder(String orderNo) {
        OrderResult orderResult = new OrderResult();
        try {
            OrderInfo orderInfo = orderManager.queryOrderById(orderNo);
            if (orderInfo ==null){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ORDER_NOT_EXIST_ERR);
            }
            BeanUtils.copyProperties(orderInfo,orderResult);
            List<ProdList> prodList = prodListManager.queryByOrderNo(orderNo);
            if (prodList != null) {
                List<ProdListResult> prodListResults = Lists.newArrayList();
                for (ProdList prod : prodList) {
                    ProdListResult result = new ProdListResult();
                    BeanUtils.copyProperties(prod, result);
                    prodListResults.add(result);
                }
                orderResult.setProdList(prodListResults);
            }
        } catch (Exception e) {
            logger.error("订单["+orderNo+"]查询失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(orderResult);
    }

    @Override
    public ResponseVo queryOrderList(String custNo, Integer page, Integer pageSize) {
        List<OrderResult> orderResults = Lists.newArrayList();
        try {
            List<OrderInfo> orderInfo = orderManager.queryOrderByPage(custNo,page,pageSize);
            for (OrderInfo info : orderInfo){
                OrderResult result = new OrderResult();
                BeanUtils.copyProperties(info,result);
                orderResults.add(result);
            }
        } catch (Exception e) {
            logger.error("用户["+custNo+"]订单列表查询失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(orderResults);
    }

    @Override
    public ResponseVo paid(String orderNo) {
        try {
            OrderInfo orderInfo = orderManager.queryOrderById(orderNo);
            if (orderInfo ==null){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ORDER_NOT_EXIST_ERR);
            }
            if (orderInfo.getStatus().equals( OrderStatusEnum.REVOKED.getStatus())){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ORDER_REVOKED_ERR);
            }
            if(orderInfo.getStatus().equals( OrderStatusEnum.INIT.getStatus())){
                OrderInfo newOrder = new OrderInfo();
                newOrder.setId(orderNo);
                newOrder.setStatus(OrderStatusEnum.PAID.getStatus());
                orderInfoService.updateBySelective(newOrder);
            }
        } catch (Exception e) {
            logger.error("订单["+orderNo+"]查询失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(true);
    }

    @Override
    public ResponseVo revoke(String orderNo) {
        try {
            OrderInfo orderInfo = orderManager.queryOrderById(orderNo);
            if (orderInfo ==null){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ORDER_NOT_EXIST_ERR);
            }
            if (orderInfo.getStatus().equals( OrderStatusEnum.REVOKED.getStatus())){
                return ResponseFactory.buildSuccessResponse(true);
            }
            if(orderInfo.getStatus().equals( OrderStatusEnum.INIT.getStatus()) ||
                    orderInfo.getStatus().equals( OrderStatusEnum.PAID.getStatus())){
                OrderInfo newOrder = new OrderInfo();
                newOrder.setId(orderNo);
                newOrder.setStatus(OrderStatusEnum.REVOKED.getStatus());
                orderInfoService.updateBySelective(newOrder);
            }else {
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_CAN_NOT_REVOKE_ERR);
            }
        } catch (Exception e) {
            logger.error("订单["+orderNo+"]查询失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(true);
    }

    @Override
    public ResponseVo finish(String orderNo) {
        try {
            OrderInfo orderInfo = orderManager.queryOrderById(orderNo);
            if (orderInfo ==null){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ORDER_NOT_EXIST_ERR);
            }
            if (orderInfo.getStatus().equals( OrderStatusEnum.REVOKED.getStatus())){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ORDER_REVOKED_ERR);
            }
            if (orderInfo.getStatus().equals( OrderStatusEnum.FINISHED.getStatus()) ||
                    orderInfo.getStatus().equals( OrderStatusEnum.COMMENTED.getStatus())){
                return ResponseFactory.buildSuccessResponse(true);
            }
            OrderInfo newOrder = new OrderInfo();
            newOrder.setId(orderNo);
            newOrder.setStatus(OrderStatusEnum.FINISHED.getStatus());
            orderInfoService.updateBySelective(newOrder);
        } catch (Exception e) {
            logger.error("订单["+orderNo+"]查询失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(true);
    }

    @Override
    public ResponseVo applyForReturn(String orderNo) {
        try {
            OrderInfo orderInfo = orderManager.queryOrderById(orderNo);
            if (orderInfo ==null){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ORDER_NOT_EXIST_ERR);
            }
            if (orderInfo.getStatus().equals( OrderStatusEnum.REVOKED.getStatus())){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ORDER_REVOKED_ERR);
            }
            if (orderInfo.getStatus().equals( OrderStatusEnum.RETURNING.getStatus()) ||
                    orderInfo.getStatus().equals( OrderStatusEnum.RETURNED.getStatus())){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ORDER_REVOKED_ERR);
            }
            if (orderInfo.getStatus().equals( OrderStatusEnum.FINISHED.getStatus()) ||
                    orderInfo.getStatus().equals( OrderStatusEnum.COMMENTED.getStatus())){
                OrderInfo newOrder = new OrderInfo();
                newOrder.setId(orderNo);
                newOrder.setStatus(OrderStatusEnum.RETURNING.getStatus());
                orderInfoService.updateBySelective(newOrder);
                return ResponseFactory.buildSuccessResponse(true);
            }

        } catch (Exception e) {
            logger.error("订单["+orderNo+"]查询失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(true);
    }
}
