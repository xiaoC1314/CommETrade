package com.zhzx.uip.api.filter;

import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    public static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    public static final String[] WHITE_LIST = {
            "127.0.0.1", "0:0:0:0:0:0:0:1"};

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isInWhiteList(httpRequest.getRemoteAddr())) {//白名单
            chain.doFilter(request, response);// 放行。让其走到下个链或目标资源中
        } else {
            ResponseVo result = new ResponseVo();
            result.setSuccess(false);
            result.setMessage(ErrorEnum.UIP_COMM_NO_AUTH_ERROR.getErrorMsg());
            result.setCode(ErrorEnum.UIP_COMM_NO_AUTH_ERROR.getErrorCode());
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.getOutputStream().write(JSONUtils.jsonEncode(result).getBytes("UTF-8"));
        }
    }

    private boolean isInWhiteList(String ip) {
        logger.info("进入的IP为：" + ip);
        if (StringUtils.isBlank(ip))
            return false;

        for (String whiteList : WHITE_LIST) {
            if (ip.equals(whiteList) || ip.contains(whiteList))
                return true;
        }
        return true;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
