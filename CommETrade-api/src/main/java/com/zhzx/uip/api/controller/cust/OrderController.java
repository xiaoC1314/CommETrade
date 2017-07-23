package com.zhzx.uip.api.controller.cust;

import com.zhzx.uip.api.controller.BaseController;
import com.zhzx.uip.api.cust.model.OrderParam;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.ResponseFactory;
import com.zhzx.uip.commons.utils.StringUtil;
import com.zhzx.uip.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fu on 2017/7/17.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{


    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping("create_order")
    public ResponseVo createOrder(OrderParam param){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(param.getAddNo()),"地址编号不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(param.getCustNo()),"客户编号不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[addParam ={}]参数校验失败:{}", new Object[]{param, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return orderService.createOrder(param);
    }


    @ResponseBody
    @RequestMapping("order_detail")
    public ResponseVo order_detail(String orderNo){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(orderNo),"订单编号不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[orderNo ={}]参数校验失败:{}", new Object[]{orderNo, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return orderService.queryOrder(orderNo);
    }

    @ResponseBody
    @RequestMapping("order_list")
    public ResponseVo queryOrderList(String custNo,Integer page,Integer pageSize){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(custNo),"客户编号不能为空");
            page = page ==null?1:page;
            pageSize = pageSize == null?10:pageSize;
        }catch (IllegalArgumentException e) {
            errorLogger.error("[custNo ={}]参数校验失败:{}", new Object[]{custNo, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return orderService.queryOrderList(custNo,page,pageSize);
    }

    @ResponseBody
    @RequestMapping("paid")
    public ResponseVo paid(String orderNo){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(orderNo),"订单编号不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[orderNo ={}]参数校验失败:{}", new Object[]{orderNo, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return orderService.paid(orderNo);
    }

    @ResponseBody
    @RequestMapping("revoke")
    public ResponseVo revoke(String orderNo){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(orderNo),"订单编号不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[orderNo ={}]参数校验失败:{}", new Object[]{orderNo, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return orderService.revoke(orderNo);
    }


    @ResponseBody
    @RequestMapping("finish")
    public ResponseVo finish(String orderNo){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(orderNo),"订单编号不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[orderNo ={}]参数校验失败:{}", new Object[]{orderNo, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return orderService.finish(orderNo);
    }

    @ResponseBody
    @RequestMapping("apply_return")
    public ResponseVo applyForReturn(String orderNo){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(orderNo),"订单编号不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[orderNo ={}]参数校验失败:{}", new Object[]{orderNo, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return orderService.applyForReturn(orderNo);
    }
}
