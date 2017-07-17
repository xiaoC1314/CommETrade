package com.zhzx.uip.api.controller.cust;

import com.zhzx.uip.api.controller.BaseController;
import com.zhzx.uip.api.cust.model.RegisterParam;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.ResponseFactory;
import com.zhzx.uip.commons.utils.StringUtil;
import com.zhzx.uip.service.manager.cust.CustManager;
import com.zhzx.uip.service.cust.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fu on 2017/7/8.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{


    @Autowired
    CustService custService;

    @Autowired
    CustManager custManager;

    @ResponseBody
    @RequestMapping("check_phone")
    public ResponseVo checkPhone(String phone){
        ResponseVo responseVo;
        try {
            Assert.isTrue(StringUtil.isNumeric(phone), "手机号码必须为数字");
            Assert.isTrue(phone.length() == 11, "手机号码格式不正确");
        }catch (IllegalArgumentException e){
            errorLogger.error("[phone={}]参数校验失败:{}",new Object[]{phone,e.getMessage()});
            return ResponseFactory.buildFailResponse(ErrorEnum.UIP_COMM_PARAM_ERROR);
        }
        return custService.checkPhone(phone);
    }

    @ResponseBody
    @RequestMapping("register")
    public ResponseVo register(RegisterParam param) {
        ResponseVo responseVo;
        try {
            Assert.isTrue(custService.checkPhone(param.getPhone()).getSuccess(), "手机号码已注册");
            Assert.isTrue(param.getPhone().length() == 11, "手机号码格式不正确");
            Assert.isTrue(!StringUtil.isEmptyString(param.getPassword()),"密码不能为空");
            Assert.isTrue(param.getPassword().length()>=6 && param.getPassword().length()<=12,"密码长度应在6-12位");
            Assert.notNull(param.getSmsCode(),"验证码不能为空");
        }catch (IllegalArgumentException e){
            errorLogger.error("[phone={},password={},smsCode={}]参数校验失败:{}",new Object[]{param.getPhone(),param.getPassword(),param.getSmsCode(),e.getMessage()});
            return new ResponseVo(false, ErrorEnum.UIP_COMM_PARAM_ERROR.getErrorCode(),
                    e.getMessage(),false);
        }

        if(custService.register(param)){
            responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS,true);
        }else {
            responseVo = new ResponseVo(false, ErrorEnum.COMM_PHONE_EXIST_ERR,false);
        }
        return responseVo;
    }

    @ResponseBody
    @RequestMapping("profile_update")
    public ResponseVo profileUpdate(RegisterParam param) {
        try {
            Assert.isTrue(!StringUtil.isEmptyString(param.getPhone()), "手机号码不能为空");
            Assert.isTrue(param.getPhone().length() == 11, "手机号码格式不正确");
        }catch (IllegalArgumentException e){
            errorLogger.error("[phone={}]参数校验失败:{}",new Object[]{param.getPhone(),e.getMessage()});
            return new ResponseVo(false, ErrorEnum.UIP_COMM_PARAM_ERROR.getErrorCode(),
                    e.getMessage(),false);
        }
        return custService.profileUpdate(param);
    }

    /**
     *
     * @param phone
     * @param password
     * @param type 1,密码登录；2.短信验证码登录
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public ResponseVo login(String phone, String password, Integer type) {
        try {
            Assert.isTrue(!StringUtil.isEmptyString(phone),"手机号码不能为空");
            Assert.notNull(type,"登录类型不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(password),type==1?"密码不能为空":"短信验证码不能为空");
        }catch (IllegalArgumentException e){
            errorLogger.error("[phone={},password={},type={}]参数校验失败:{}",new Object[]{phone,password,type,e.getMessage()});
            return new ResponseVo(false, ErrorEnum.UIP_COMM_PARAM_ERROR.getErrorCode(),
                    e.getMessage(),false);
        }
        return  custService.login(phone,password,type);
    }

    @ResponseBody
    @RequestMapping("change_password")
    public ResponseVo changePassword(String phone,String password,String newPassword) {
        try {
            Assert.isTrue(!StringUtil.isEmptyString(phone),"手机号码不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(password),"密码不能为空");
            Assert.isTrue(!StringUtil.isEmptyString(newPassword),"新密码不能为空");
            Assert.isTrue(!password.equals(newPassword),"新旧密码不能相同");
            Assert.isTrue(newPassword.length()>=6 && newPassword.length()<=12,"密码长度应在6-12位");
        }catch (IllegalArgumentException e){
            errorLogger.error("[phone={},password={},newPassword={}]参数校验失败:{}",new Object[]{phone,password,newPassword,e.getMessage()});
            return new ResponseVo(false, ErrorEnum.UIP_COMM_PARAM_ERROR.getErrorCode(),
                    e.getMessage(),false);
        }
        return  custService.changePassword(phone,password,newPassword);
    }
}
