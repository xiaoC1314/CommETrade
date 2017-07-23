package com.zhzx.uip.api.controller.cust;

import com.zhzx.uip.api.controller.BaseController;
import com.zhzx.uip.api.cust.model.AddParam;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.ResponseFactory;
import com.zhzx.uip.commons.utils.StringUtil;
import com.zhzx.uip.service.cust.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fu on 2017/7/18.
 */
@Controller
@RequestMapping("address")
public class AddressController extends BaseController{


    @Autowired
    AddressService addressService;

    @ResponseBody
    @RequestMapping("add_address")
    public ResponseVo addAddreess(AddParam addParam){
        try {
            Assert.isTrue(!StringUtil.isEmptyString(addParam.getCustNo()),"客户编号不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(addParam.getAddress()),"地址不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(addParam.getReceiverName()),"收件人地址不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(addParam.getReceiverPhone()),"收件人电话不能为空");
        }catch (IllegalArgumentException e){
            errorLogger.error("[addParam ={}]参数校验失败:{}",new Object[]{addParam,e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return  addressService.addAddress(addParam);
    }
}
