package com.zhzx.uip.service.manager.order;

import com.zhzx.dao.bean.order.Cart;
import com.zhzx.dao.bean.order.OrderInfo;
import com.zhzx.dao.bean.order.ProdList;
import com.zhzx.dao.bean.prod.ProdInfo;
import com.zhzx.dao.model.order.OrderInfoModel;
import com.zhzx.dao.service.order.CartService;
import com.zhzx.dao.service.order.OrderInfoService;
import com.zhzx.dao.service.order.ProdListService;
import com.zhzx.dao.service.prod.ProdInfoService;
import com.zhzx.uip.api.cust.model.OrderParam;
import com.zhzx.uip.commons.enums.OrderStatusEnum;
import com.zhzx.uip.commons.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fu on 2017/7/18.
 */
@Component
public class OrderManager {


    @Autowired
    CartManager cartManager;

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    ProdInfoService prodInfoService;

    @Autowired
    ProdListService prodListService;

    @Autowired
    CartService cartService;


    @Transactional
    public String createOrder(OrderParam param) throws Exception {
        List<Cart> carts = cartManager.queryAllCartForUpdate(param.getCustNo(),true);
        if (carts ==null || carts.size()==0){
            throw new BusinessException("购物车为空！无法下单");
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAddNo(param.getAddNo());
        orderInfo.setCustNo(param.getCustNo());
        orderInfo.setStatus(OrderStatusEnum.INIT.getStatus());
        orderInfoService.insert(orderInfo);
        OrderInfoModel orderInfoModel = new OrderInfoModel();
        BeanUtils.copyProperties(orderInfo,orderInfoModel);
        orderInfoModel.getNavigate().setOrderField("create_time");
        OrderInfo order = (OrderInfo)orderInfoService.selectByModel(orderInfoModel).get(0);
        for (Cart cart : carts){
            if (cart.getNum() ==0){
                continue;
            }
            Cart updateCart = new Cart();
            updateCart.setOrderNo(order.getId());
            updateCart.setId(cart.getId());
            cartService.updateBySelective(updateCart);
            ProdInfo prodInfo = (ProdInfo)prodInfoService.selectById(cart.getProdNo());
            ProdList prodList = new ProdList();
            prodList.setOrderNo(order.getId());
            prodList.setCustNo(param.getCustNo());
            prodList.setProdName(prodInfo.getName());
            prodList.setProdNum(cart.getNum());
            prodList.setProdNo(prodInfo.getId());
            prodList.setProdPrice(prodInfo.getPrice());
            prodListService.insert(prodList);
        }
        return order.getId();
    }

    public OrderInfo queryOrderById(String id) throws Exception {
        OrderInfo orderInfo = (OrderInfo) orderInfoService.selectById(id);
        return orderInfo;
    }

    public List<OrderInfo> queryOrderByPage(String custNo,Integer page,Integer pageSize) throws Exception {
        OrderInfoModel orderInfoModel = new OrderInfoModel();
        orderInfoModel.setCustNo(custNo);
        orderInfoModel.getNavigate().setPageSize(pageSize);
        orderInfoModel.getNavigate().setPageId(page);
        orderInfoModel.getNavigate().setOrderField("create_time");
        List<OrderInfo> orderInfos =  orderInfoService.selectByModelAsPage(orderInfoModel);
        return orderInfos;
    }
}
