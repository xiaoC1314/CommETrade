package com.zhzx.uip.service.manager.order;

import com.zhzx.dao.bean.order.ProdList;
import com.zhzx.dao.model.order.ProdListModel;
import com.zhzx.dao.service.order.ProdListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fu on 2017/7/20.
 */
@Component
public class ProdListManager {




    @Autowired
    ProdListService prodListService;


    public List<ProdList> queryByOrderNo(String orderNo) throws Exception {
        ProdListModel model = new ProdListModel();
        model.setOrderNo(orderNo);
        List<ProdList> prodList = prodListService.selectByModel(model);
        return prodList;
    }
}
