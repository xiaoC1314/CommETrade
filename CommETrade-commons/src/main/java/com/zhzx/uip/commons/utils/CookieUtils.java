package com.zhzx.uip.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cookies 的工具类
 *
 */
public class CookieUtils {

	private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);

	// 设置cookie的有效时间 23小时 单位 s
	public static final int COOKIE_EXP = 1 * 60 * 60 * 23;

	/**
	 * 自动登陆 cookie 有效时间 12小时 单位 s
	 */
	public static final int AUTO_LOGIN_COOKIE_EXP = 1 * 60 * 60 * 12;

	/**
	 * 获取cookie 值
	 * 
	 * @param name
	 *            cookie 的 name
	 * @param cks
	 *            cookie数组
	 * @return value
	 */
	public static String getCooKieValue(String name, Cookie[] cks) {
		String result = null;

		if (null == cks) {
			return result;
		}

		for (Cookie item : cks) {
			if (item.getName().equals(name)) {
				try {
					result = URLDecoder.decode(item.getValue(), "utf-8");
				} catch (UnsupportedEncodingException e) {
					logger.error("CookieUtils getCooKieValue : " + e.getMessage(), e);
				}
				break;
			}
		}
		return result;
	}

	/**
	 * 设置cookie
	 * 
	 * @param cookieName
	 *            cookie名
	 * @return value cookie值
	 * @throws Exception
	 */
	public static void setCookie(String cookieName, String value, String domain, HttpServletResponse response) {
		setCookie(cookieName, value, COOKIE_EXP, domain, response);
	}

	public static void setCookie(String cookieName, String value, HttpServletResponse response) {
		setCookie(cookieName, value, COOKIE_EXP, null, response);
	}

	public static void setCookie(String cookieName, String value, int exp, HttpServletResponse response) {
		setCookie(cookieName, value, exp, null, response);
	}

	/**
	 * 设置cookie
	 * 
	 * @param cookieName
	 *            cookie名
	 * @return value cookie值
	 * @throws Exception
	 */
	public static void setNormalCookie(String cookieName, String value, HttpServletResponse response) {
		setCookie(cookieName, value, COOKIE_EXP, null, response);
	}

	/**
	 * 设置cookie
	 * 
	 * @param cookieName
	 *            cookie名
	 * @param value
	 *            cookie值
	 * @param max_age
	 *            有效时间
	 * @throws Exception
	 */
	public static void setCookie(String cookieName, String value, int max_age, String domain,
			HttpServletResponse response) {
		// 设置cookie
		try {
			if (null != value)
				value = URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("CookieUtils setCookie : " + e.getMessage(), e);
		}
		Cookie cookie = new Cookie(cookieName, value);
//        cookie.setSecure(true);
		cookie.setPath("/");

		// 设置过期时间
		cookie.setMaxAge(max_age);
		if (StringUtils.isNotBlank(domain))
			cookie.setDomain(domain);

		response.setHeader("P3P",
				"CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");// 允许引用的第三方网站跨域设置cookie
		response.addCookie(cookie);
	}

	public static void setSessionCookie(String cookieName, String value, String domain, HttpServletResponse response) {
		// 设置cookie
		try {
			if (null != value)
				value = URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("CookieUtils setCookie : " + e.getMessage(), e);
		}
		Cookie cookie = new Cookie(cookieName, value);
//        cookie.setSecure(true);
		cookie.setPath("/");

		if (StringUtils.isNotBlank(domain))
			cookie.setDomain(domain);

		response.setHeader("P3P",
				"CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");// 允许引用的第三方网站跨域设置cookie
		response.addCookie(cookie);
	}

	/**
	 * 清除cookie
	 * 
	 * @param cookieName
	 *            cookie名
	 * @return cookie值
	 */
	public static void removeCookie(String cookieName, HttpServletResponse response) {
		setCookie(cookieName, null, 0, null, response);
	}

	public static void removeCookie(String cookieName, String domain, HttpServletResponse response) {
		setCookie(cookieName, null, 0, domain, response);
	}
	
	/**
	 * 获取Cookie Value
	 * 
	 * @param key
	 * @return
	 */
	public static String getCookieValue(String key, HttpServletRequest request) {
		if (StringUtils.isBlank(key))
			return null;
		Cookie[] cks = request.getCookies();
		if (ArrayUtils.isEmpty(cks)) {
			return null;
		} else {
			return CookieUtils.getCooKieValue(key, cks);
		}
	}

}
