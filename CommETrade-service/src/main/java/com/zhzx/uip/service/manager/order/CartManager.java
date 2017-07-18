package com.zhzx.uip.service.manager.order;

import com.zhzx.dao.bean.order.Cart;
import com.zhzx.dao.service.order.CartService;
import com.zhzx.uip.api.cust.model.CartParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fu on 2017/7/18.
 */
@Component
public class CartManager {

    private static final Logger logger = LoggerFactory.getLogger(CartManager.class);


    @Autowired
    CartService cartService;

    public Cart queryCartForUpdate(String custNo,String prodNo) throws Exception {
        Map<String,String> cartModel  = new HashMap<String, String>();
        cartModel.put("custNo",custNo);
        cartModel.put("prodNo",prodNo);
        cartModel.put("queryCondition","and order_no is null");
        cartModel.put("orderCondition","cust_no limit 1 for update");
        List<Cart> carts =  cartService.selectByMap(cartModel);
        if (carts != null){
            return carts.get(0);
        }
        return null;
    }

    public List<Cart> queryAllCartForUpdate(String custNo,boolean update) throws Exception {
        Map<String,String> cartModel  = new HashMap<String, String>();
        cartModel.put("custNo",custNo);
        cartModel.put("queryCondition","and order_no is null");
        cartModel.put("orderCondition","cust_no "+(update?"for update":""));
        return cartService.selectByMap(cartModel);
    }

    @Transactional
    public void addCart(CartParam cartParam) throws Exception {
        Cart cart = queryCartForUpdate(cartParam.getCustNo(),cartParam.getProdNo());
        if (cart == null){
            cart = new Cart();
            BeanUtils.copyProperties(cartParam,cart);
            cartService.insert(cart);
        }else {
            cart.setNum(cart.getNum()+cartParam.getNum());
            cartService.updateBySelective(cart);
        }
    }

    @Transactional
    public void subCart(CartParam cartParam) throws Exception {
        Cart cart = queryCartForUpdate(cartParam.getCustNo(),cartParam.getProdNo());
        if (cart != null){
            int num = cart.getNum()-cartParam.getNum()>0?cart.getNum()-cartParam.getNum():0;
            cart.setNum(num);
            cartService.updateBySelective(cart);
        }
    }

    @Transactional
    public void removeCart(CartParam cartParam) throws Exception {
        Cart cart = queryCartForUpdate(cartParam.getCustNo(),cartParam.getProdNo());
        if (cart != null){
            cartService.delete(cart.getId());
        }
    }

    @Transactional
    public void clearCart(CartParam cartParam) throws Exception {
        List<Cart> carts = queryAllCartForUpdate(cartParam.getCustNo(),true);
        if (carts == null){
            return;
        }
        String[] ids = new String[carts.size()];
        for (int i = 0;i<carts.size();i++){
            ids[i] = carts.get(i).getId();
        }
        cartService.delete(ids);
    }


}
