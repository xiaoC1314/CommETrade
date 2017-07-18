package com.zhzx.uip.api.controller.cust;

import com.zhzx.uip.api.controller.BaseController;
import com.zhzx.uip.api.cust.model.OrderParam;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
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
            return new ResponseVo(false, ErrorEnum.UIP_COMM_PARAM_ERROR.getErrorCode(),
                    e.getMessage(), false);
        }
        return orderService.createOrder(param);
    }
}
