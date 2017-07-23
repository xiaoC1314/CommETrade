package com.zhzx.uip.service.order.impl;

import com.google.common.collect.Lists;
import com.zhzx.dao.bean.order.Cart;
import com.zhzx.dao.service.prod.ProdInfoService;
import com.zhzx.uip.api.cust.model.CartParam;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.ResponseFactory;
import com.zhzx.uip.service.manager.order.CartManager;
import com.zhzx.uip.service.order.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by fu on 2017/7/18.
 */
public class CartServiceImpl implements CartService {


    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    CartManager cartManager;

    @Autowired
    ProdInfoService prodInfoService;


    @Override
    public ResponseVo addCart(CartParam cartParam) {
        try {
            Object prodInfo = prodInfoService.selectById(cartParam.getProdNo());
            if (prodInfo ==null){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_PROD_NOT_EXIST_ERR);
            }
            cartManager.addCart(cartParam);
        } catch (Exception e) {
            logger.error("用户[{}]添加购物车失败！{}",new String[]{cartParam.getCustNo(),e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(true);
    }

    @Override
    public ResponseVo subCart(CartParam cartParam) {
        try {
            Object prodInfo = prodInfoService.selectById(cartParam.getProdNo());
            if (prodInfo ==null){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_PROD_NOT_EXIST_ERR);
            }
            cartManager.subCart(cartParam);
        } catch (Exception e) {
            logger.error("用户[{}]减少购物车失败！{}",new String[]{cartParam.getCustNo(),e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(true);
    }

    @Override
    public ResponseVo removeCart(CartParam cartParam) {
        try {
            Object prodInfo = prodInfoService.selectById(cartParam.getProdNo());
            if (prodInfo ==null){
                return ResponseFactory.buildFailResponse(ErrorEnum.COMM_PROD_NOT_EXIST_ERR);
            }
            cartManager.removeCart(cartParam);
        } catch (Exception e) {
            logger.error("用户[{}]删除一类商品失败！{}",new String[]{cartParam.getCustNo(),e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(true);
    }

    @Override
    public ResponseVo clearCart(CartParam cartParam) {
        try {
            cartManager.clearCart(cartParam);
        } catch (Exception e) {
            logger.error("用户[{}]清空购物车失败！{}",new String[]{cartParam.getCustNo(),e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(true);
    }

    @Override
    public ResponseVo getAllCart(String custNo) {
        List<Cart> result = Lists.newArrayList();
        try {
            List<Cart> carts = cartManager.queryAllCartForUpdate(custNo, false);
            for (Cart cart : carts){
                if (cart.getNum() != 0){
                    result.add(cart);
                }
            }
        } catch (Exception e) {
            logger.error("用户[{}]查询购物车失败！{}",new String[]{custNo,e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }
        return ResponseFactory.buildSuccessResponse(result);
    }
}
