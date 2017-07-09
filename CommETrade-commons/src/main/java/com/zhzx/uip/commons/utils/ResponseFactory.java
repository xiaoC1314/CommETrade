package com.zhzx.uip.commons.utils;

import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;

/**
 * Created by fu on 2017/7/9.
 */
public class ResponseFactory {

    public static ResponseVo buildSuccessResponse(Object data){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ErrorEnum.COMM_SUCCESS.getErrorCode());
        responseVo.setMessage(ErrorEnum.COMM_SUCCESS.getErrorMsg());
        responseVo.setData(data);
        responseVo.setSuccess(true);
        return responseVo;
    }

    public static ResponseVo buildFailResponse(ErrorEnum errorEnum){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(errorEnum.getErrorCode());
        responseVo.setMessage(errorEnum.getErrorMsg());
        responseVo.setSuccess(false);
        return responseVo;
    }
}
