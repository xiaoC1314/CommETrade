package com.zhzx.uip.service.manager.cust;

import com.zhzx.dao.bean.cust.CustInfo;
import com.zhzx.dao.model.cust.CustInfoModel;
import com.zhzx.dao.service.cust.CustInfoService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fu on 2017/7/13.
 */
@Component
public class CustManager {
    private static final Logger logger = LoggerFactory.getLogger(CustManager.class);


    @Autowired
    private CustInfoService custInfoService;


    public CustInfo queryByPhone(String phone) throws Exception{

        CustInfoModel model = new CustInfoModel();
        model.setPhone(phone);
        List<CustInfo> listret = ( List<CustInfo>)custInfoService.selectByModel(model);
        if (CollectionUtils.isNotEmpty(listret)) {
            return listret.get(0);
        }else{
            return null;
        }
    }



}
