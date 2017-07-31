package com.zhzx.uip.service.cust.impl;

import com.google.common.collect.Lists;
import com.zhzx.dao.bean.cust.Address;
import com.zhzx.dao.model.cust.AddressModel;
import com.zhzx.uip.api.cust.model.AddParam;
import com.zhzx.uip.api.cust.model.AddressResult;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.ResponseFactory;
import com.zhzx.uip.service.cust.AddressService;
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
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    com.zhzx.dao.service.cust.AddressService addressService;


    @Override
    public ResponseVo addAddress(AddParam param) {
        Address address = new Address();
        BeanUtils.copyProperties(param,address);
        try {
            addressService.insert(address);
        } catch (Exception e) {
            logger.error("用户["+param.getCustNo()+"]添加地址失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }

        return  ResponseFactory.buildSuccessResponse(true);
    }

    @Override
    public ResponseVo queryAddressList(String custNo,Integer page,Integer pageSize) {
        List<AddressResult> resultList = Lists.newArrayList();
        try {
            AddressModel addressModel = new AddressModel();
            addressModel.setCustNo(custNo);
            addressModel.getNavigate().setPageSize(pageSize);
            addressModel.getNavigate().setPageCount(page);
            List<Address> list = addressService.selectByModelAsPage(addressModel);
            if (list != null && list.size()!=0){
                for (Address address :list) {
                    AddressResult result = new AddressResult();
                    BeanUtils.copyProperties(address,result);
                    resultList.add(result);
                }
            }
        } catch (Exception e) {
            logger.error("用户["+custNo+"]查询地址失败！",e);
            return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
        }

        return  ResponseFactory.buildSuccessResponse(resultList);
    }
}
