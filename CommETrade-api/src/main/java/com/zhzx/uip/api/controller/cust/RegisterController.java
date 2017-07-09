package com.zhzx.uip.api.controller.cust;

import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.StringUtil;
import com.zhzx.uip.service.cust.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fu on 2017/7/8.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {


    @Autowired
    CustService custService;

    @RequestMapping("check_phone")
    public ResponseVo checkPhone(String phone){
        ResponseVo responseVo;
        try {
            Assert.isTrue(StringUtil.isNumeric(phone), "手机号码必须为数字");
            Assert.isTrue(phone.length() == 11, "手机号码格式不正确");
        }catch (IllegalArgumentException e){
            return new ResponseVo(false, ErrorEnum.UIP_COMM_PARAM_ERROR.getErrorCode(),
                    e.getMessage(),false);
        }
        if (custService.checkPhone(phone)){
            responseVo = new ResponseVo(false, ErrorEnum.COMM_PHONE_EXIST_ERR,false);
        }else {
            responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS,true);
        }
        return responseVo;
    }

//    @RequestMapping("register")
//    public ResponseVo register(RegisterParam param){
//        ResponseVo responseVo;
//        try {
//            Assert.isTrue(custService.checkPhone(param.getPhone()), "手机号码已注册");
//            Assert.isTrue(param.getPhone().length() == 11, "手机号码格式不正确");
//
//        }catch (IllegalArgumentException e){
//
//        }
//        return  responseVo;
//    }
}
