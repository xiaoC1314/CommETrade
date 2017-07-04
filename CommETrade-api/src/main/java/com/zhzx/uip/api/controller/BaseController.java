package com.zhzx.uip.api.controller;

import com.zhzx.uip.commons.utils.DateUtil;
import com.zhzx.uip.commons.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wangfeng on 2016/3/26.
 */
public class BaseController {

    protected static final org.slf4j.Logger errorLogger = LoggerFactory.getLogger("LOGGER_ERROR");
    protected static final Logger infoLogger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView handleException(HttpServletRequest request,
                                  Exception ex,
                                  HttpServletResponse response){
        errorLogger.error("未处理异常", ex);
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("err_exception", ex.getStackTrace());
        return mav;
    }

    public boolean validataSignMsg(String timestamp,String signMsg){
        if(StringUtil.isEmptyString(timestamp) || StringUtil.isEmptyString(signMsg))
            return false;

        boolean isvalid = false;
        String now = DateUtil.getCurDateTimeStr();
        if( Math.abs(DateUtil.compareTimeStr(now,timestamp))/1000/60 > 10){//超过10分钟,怕机器时间不统一
            return false;
        }
        return signMsg.equals(signMsg(timestamp));
    }
    /**
     * 加密规则
     * 时间戳加密后,拼接加密串和时间戳再md5签名
     * @param timestamp
     * @return
     */
    public String signMsg(String timestamp){
        try {
            String timestampMd5 = StringUtil.toMD5(timestamp);
            return StringUtil.toMD5(timestampMd5 + timestamp);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
