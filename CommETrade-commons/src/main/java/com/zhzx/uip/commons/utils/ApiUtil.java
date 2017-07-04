package com.zhzx.uip.commons.utils;


import com.google.common.collect.Lists;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public final class ApiUtil {


    /**
     * 校验签名
     * @param request
     * @param secretKey
     * @param ignores
     * @return
     */
	public static boolean checkSign(HttpServletRequest request, String secretKey, Set<String> ignores){
		String sign = request.getParameter("sign");
		return StringUtils.equalsIgnoreCase(sign, sign(request, secretKey, ignores));
		
	}
	
	/**
	 * 加密算法（） 含secretKey, 过滤字段
	 * @param request
	 * @param secretKey
	 * @return 计算得到的签名
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String sign(HttpServletRequest request, String secretKey, Set<String> ignores){
		Map params = new HashMap(request.getParameterMap());
		if(!CollectionUtils.isEmpty(ignores)){
			for(String ingore : ignores){
				params.remove(ingore);
			}
		}
		return sign(params, secretKey);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String sign(Map params, String secretKey) {
		SortedMap<String, Object> sortedParams = new TreeMap<String, Object>(params);
		sortedParams.remove("sign");
		StringBuilder sb = new StringBuilder();
		for (String key : sortedParams.keySet()) {
			Object obj = sortedParams.get(key);
			if(obj instanceof String[]) {
				ArrayList<String> list = Lists.newArrayList((String[]) obj);
				Collections.sort(list);
				for(String v : list) {
					sb.append(key).append("=").append(StringUtils.defaultIfEmpty(v, "")).append("|");
				}
			} else {
				String val = "" + sortedParams.get(key);
				sb.append(key).append("=").append(StringUtils.defaultIfEmpty(val, "")).append("|");
			}
		}
        return DigestUtils.md5Hex(sb.append(secretKey).toString());
	}

//    public static void main(String[] args) {
//        System.out.println(DigestUtils.md5Hex("sadadada"));
//    }
}
