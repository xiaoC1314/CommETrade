package com.zhzx.uip.service.order;

import com.zhzx.uip.api.cust.model.CartParam;
import com.zhzx.uip.commons.module.ResponseVo;

/**
 * Created by fu on 2017/7/18.
 */
public interface CartService {


    ResponseVo addCart(CartParam cartParam);


    ResponseVo subCart(CartParam cartParam);

    ResponseVo removeCart(CartParam cartParam);

    ResponseVo clearCart(CartParam cartParam);

    ResponseVo getAllCart(String custNo);
}
