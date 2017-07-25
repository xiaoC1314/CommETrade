package com.zhzx.uip.api.controller.cust;

import com.zhzx.uip.api.controller.BaseController;
import com.zhzx.uip.api.cust.model.CartParam;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.ResponseFactory;
import com.zhzx.uip.commons.utils.StringUtil;
import com.zhzx.uip.service.order.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fu on 2017/7/18.
 */
@Controller
@RequestMapping("/cart")
public class CartController extends BaseController{


    @Autowired
    CartService cartService;

    @ResponseBody
    @RequestMapping("add_cart")
    public ResponseVo addCart(CartParam param){

        try{
            Assert.isTrue(!StringUtil.isEmptyString(param.getCustNo()),"客户编号不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(param.getProdNo()),"产品编号不能为空");
            Assert.isTrue(param.getNum() != null,"产品数量不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[CartParam ={}]参数校验失败:{}", new Object[]{param, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return cartService.addCart(param);
    }

    @ResponseBody
    @RequestMapping("sub_cart")
    public ResponseVo subCart(CartParam param){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(param.getCustNo()),"客户编号不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(param.getProdNo()),"产品编号不能为空");
            Assert.isTrue(param.getNum() != null,"产品数量不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[CartParam ={}]参数校验失败:{}", new Object[]{param, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return cartService.subCart(param);
    }

    @ResponseBody
    @RequestMapping("remove_cart")
    public ResponseVo removeCart(CartParam param){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(param.getCustNo()),"客户编号不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(param.getProdNo()),"产品编号不能为空");
            Assert.isTrue(param.getNum() != null,"产品数量不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[CartParam ={}]参数校验失败:{}", new Object[]{param, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return cartService.removeCart(param);
    }


    @ResponseBody
    @RequestMapping("clear_cart")
    public ResponseVo clearCart(CartParam param){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(param.getCustNo()),"客户编号不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[CartParam ={}]参数校验失败:{}", new Object[]{param, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return cartService.clearCart(param);
    }

    @ResponseBody
    @RequestMapping("query_cart")
    public ResponseVo queryCart(String custNo){
        try{
            Assert.isTrue(!StringUtil.isEmptyString(custNo),"客户编号不能为空");
        }catch (IllegalArgumentException e) {
            errorLogger.error("[custNo ={}]参数校验失败:{}", new Object[]{custNo, e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return cartService.getAllCart(custNo);
    }
}
