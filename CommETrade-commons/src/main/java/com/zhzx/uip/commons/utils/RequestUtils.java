package com.zhzx.uip.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Request信息获取工具类
 *
 * @author xiaoC
 *
 */
public class RequestUtils {

	private static String serverIP = null;

	/**
	 * 取所有的请求报文头
	 * 
	 * @param request HttpServletRequest
	 * @return HTTP报文头信息
	 */
	@SuppressWarnings("unchecked")
	public static String getHttpHeader(HttpServletRequest request) {
		StringBuffer header = new StringBuffer();
		Enumeration<String> enu = request.getHeaderNames();
		while (enu.hasMoreElements()) {
			String headerName = enu.nextElement();
			String value = request.getHeader(headerName);
			header.append(headerName).append("=").append(value).append(",");
		}
		header.setLength(header.length() - 1);
		return header.toString();
	}

	/**
	 * 取服务器机器名
	 * 
	 * @param request HttpServletRequest
	 * @return 服务器机器名
	 */
	public static String getServerName(HttpServletRequest request) {
		String srvName = request.getServerName();
		return srvName;
	}

	/**
	 * 判断客户端是否启用cookie
	 * 
	 * @deprecated 此方法判断是否禁用不准确，对于第一次访问无法正确识别
	 * @param request HttpServletRequest
	 * @return 浏览器是否禁用了Cookie
	 */
	@Deprecated
	public static boolean isDisabledCookie(HttpServletRequest request) {
		boolean result = false;
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			result = true;
		}
		return result;
	}

	/**
	 * 将request中的参数值转为哈希类型
	 * 
	 * @param request HttpServletRequest
	 * @return 获取所有request参数
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getRequestMap(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();

		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String key = enu.nextElement();
			String val = request.getParameter(key);
			params.put(key, val);
		}
		return params;
	}

	/**
	 * 解析get提交方式的参数
	 * 
	 * @param request HttpServletRequest
	 * @return 获取get方式提交的参数
	 */
	public static Map<String, String> parseQueryString(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String queryString = request.getQueryString();
		if (queryString == null || queryString.trim().length() == 0) {
			return map;
		}

		int iPos = queryString.indexOf('?');
		String sParams = queryString.substring(iPos + 1);
		String[] pair = sParams.split("&");
		for (int i = 0; i < pair.length; i++) {
			int iEquals = pair[i].indexOf("=");
			String key = pair[i].substring(0, iEquals);
			String value = pair[i].substring(iEquals + 1);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 取客户端ip地址
	 * 
	 * @param request HttpServletRequest
	 * @return 客户端IP
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()=15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 取服务端IP
	 * 
	 * @param request HttpServletRequest
	 * @return 服务器IP
	 */
	public static String getServerIp(HttpServletRequest request) {

		if (serverIP != null) {
			return serverIP;
		}
		// 实际生产中发现getLocalAddr取的是域名
		try {
			InetAddress inetAddr = InetAddress.getLocalHost();
			serverIP = inetAddr.getHostAddress();
		} catch (UnknownHostException e) {
			//WebLogUtils.error("取服务端地址失败:", e);
		}
		return serverIP;
	}

	/**
	 * 取服务端端口
	 * 
	 * @param request HttpServletRequest
	 * @return 服务器端口号
	 */
	public static int getServerPort(HttpServletRequest request) {
		try {
			// Servlet2.4
			return request.getLocalPort();
		} catch (Exception e) {
			// Servlet2.3
			return request.getServerPort();
		}
	}
	
	/**
	 * 根据http报文头的User-Agent取浏览器类型
	 * 
	 * @param userAgent User-Agent头
	 * @return 浏览器信息
	 */
	public static String getBrowser(String userAgent) {
		String regExp = "(?:firefox|opera|safari|chrome|msie)[\\/: ]([\\d.a-zA-Z]+)";
		int maxLength = 40;
		return getUserAgentInfo(userAgent, regExp, maxLength);

	}

	/**
	 * 根据http报文头的User-Agent取操作系统类型
	 * 
	 * @param userAgent User-Agent头
	 * @return 操作系统信息
	 */
	public static String getOs(String userAgent) {
		String regExp = "(?:windows|linux|apple|unix)[\\/: ]([\\d.a-zA-Z ]+)";
		int maxLength = 20;
		return getUserAgentInfo(userAgent, regExp, maxLength);
	}
	
	/**
	 * 从userAgent中获取数据
	 * 
	 * @param userAgent http的userAgent
	 * @param regExp 数据正则表达
	 * @param maxLength 数据值的最大长度
	 * @return userAgent符合表达式部分数据的内容
	 */
	private static String getUserAgentInfo(String userAgent, String regExp,
			int maxLength) {
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(userAgent);
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			if (buffer.indexOf(matcher.group()) >= 0) {// 防止信息重复
				continue;
			}
			buffer.append(matcher.group());
		}
		String returnstr = buffer.toString();
		
		if (returnstr.length() > maxLength)// 不能超过数据库字段最大长度
			returnstr = returnstr.substring(0, maxLength);
		return returnstr;
	}

	/**
	 * 写入Cookie
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param cookieName cookie的名字
	 * @param cookieValue cookie的值
	 * @param expire 有效时间（秒）
	 */
	public static void writeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int expire) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(expire);
		response.addCookie(cookie);
	}

	/**
	 * 删除cookie
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param cookieName cookie的名字
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		writeCookie(request, response, cookieName, null, 0);
	}

	/**
	 * 获取cookie值
	 * 
	 * 
	 * @param request HttpServletRequest
	 * @param cookieName cookie的名字
	 * @return cookie的值。null-如果无此Cookie
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			String key = cookie.getName();
			String val = cookie.getValue();
			if (cookieName.equals(key)) {
				return val;
			}
		}

		return null;
	}

	/**
	 * 
	 * 获取Cookie名字
	 * 
	 * @param request HttpServletRequest
	 * @param value cookie的值
	 * @return 值对应的cookie的名字。""-如果无对应的cookie
	 */
	public static String getCookieName(HttpServletRequest request, String value) {
		String cookieName = "";
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return cookieName;
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			String key = cookie.getName();
			String val = cookie.getValue();
			if (value.equals(val)) {
				cookieName = key;
				break;
			}
		}
		return cookieName.toUpperCase();
	}

	/**
	 * 返回客户端和服务器端信息
	 * 
	 * @param request HttpServletRequest
	 * @return 客户端和服务端信息
	 */
	@SuppressWarnings("unchecked")
	public static String getClientAndServerInfo(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		String userAgent = request.getHeader("User-Agent");
		sb.append("服务器端IP[").append(RequestUtils.getServerIp(request)).append("]，端口[")
				.append(RequestUtils.getServerPort(request)).append("]，客户端IP[")
				.append(RequestUtils.getClientIp(request)).append("]");
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			sb.append("，客户端浏览器[").append(RequestUtils.getBrowser(userAgent)).append("]，客户端操作系统[")
					.append(RequestUtils.getOs(userAgent)).append("]\n");
		}

		HttpSession session = request.getSession(true);
		Enumeration<String> enume = session.getAttributeNames();
		if (enume.hasMoreElements()) {
			String attr = (String) enume.nextElement();
			sb.append("Session中现有信息：").append("[" + attr + "]=[" + session.getAttribute(attr) + "]");
		} else {
			sb.append("Session中没有有信息。");
		}
		while (enume.hasMoreElements()) {
			String attr = (String) enume.nextElement();
			sb.append(",[" + attr + "]=[" + session.getAttribute(attr) + "]");
		}
		sb.append("\n");
		return sb.toString();
	}

	/**
	 * 获取referer头信息
	 * 
	 * @param request HttpServletRequest
	 * @return Referer头信息
	 */
	public static String getReferer(HttpServletRequest request) {
		return request.getHeader("Referer");
	}
}
