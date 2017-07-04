package com.zhzx.uip.commons.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IpUtil {

	protected static final Logger logger = LoggerFactory.getLogger(IpUtil.class);

	// private static InetAddress inetAddress = null;
	// static{
	// try {
	// inetAddress = InetAddress.getLocalHost();
	//
	// if( inetAddress.getHostAddress().equals("127.0.0.1") ){
	// throw new UnknownHostException("please configure hostname");
	// }
	// } catch (UnknownHostException e) {
	// logger.error("InetAddress.getLocalHost error.", e);
	// try {
	// throw new UnknownHostException("please configure hostname");
	// } catch (UnknownHostException e1) {
	// logger.error("InetAddress.getLocalHost error.", e1);
	// }
	// }
	// }
	//
	// public static String getIp() {
	// if (inetAddress !=null) {
	// return inetAddress.getHostAddress();
	// }
	// return null;
	// }
	//
	// public static String getHostName() {
	// if (inetAddress !=null) {
	// return inetAddress.getHostName();
	// }
	// return null;
	// }

	public static String getClientIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");
		if (!isValidIpAddress(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (!isValidIpAddress(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!isValidIpAddress(ipAddress)) {
			ipAddress = request.getHeader("X-Real-IP");
		}
		if (!isValidIpAddress(ipAddress)) {
			ipAddress = request.getRemoteAddr();
//			if (ipAddress.equals("127.0.0.1")) {
//				// 根据网卡取本机配置的IP
//				InetAddress inet = null;
//				try {
//					inet = InetAddress.getLocalHost();
//                    ipAddress = inet.getHostAddress();
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//			}
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	public static String getClientPort(HttpServletRequest request) {
		int portNum = request.getRemotePort();
		if (portNum <= 0) {
			try {
				String port = request.getHeader("remote-port");
				return port;
			} catch (Exception e) {
				logger.error("get romote port error,error message:" + e.getMessage());
				return null;
			}
		}

		return String.valueOf(portNum);
	}

	public static String getServerIpAddr(HttpServletRequest request) {
		StringBuffer serverIps = new StringBuffer();
		Enumeration e1;
		try {
			e1 = (Enumeration) NetworkInterface.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				Enumeration e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					InetAddress ia = (InetAddress) e2.nextElement();
					if (ia instanceof Inet6Address)
						continue; // omit IPv6 address

					// if (!ia.isSiteLocalAddress() && !ia.isLoopbackAddress()
					// && ia.getHostAddress().indexOf(":") == -1) {
					// serverIp.append(ia.getHostAddress());
					// }

					serverIps.append(ia.getHostAddress());
					serverIps.append(",");
				}
			}
		} catch (SocketException e) {
			logger.error("getServerIpAddr 出错:" + e.getMessage(), e);
		}

		String serverIpsStr = serverIps.toString();
		if (StringUtils.isNotBlank(serverIpsStr)) {
			String[] serverIpArray = serverIpsStr.split(",");
			if (null != serverIpArray && serverIpArray.length != 0) {
				for (String serverIp : serverIpArray) {
					if (!"127.0.0.1".equals(serverIp)) {
						return serverIp;
					}
				}
			}
		}

		return null;
	}

	public static String getServerPort(HttpServletRequest request) {
		int localPort = request.getServerPort();
		return String.valueOf(localPort);
	}

	private static boolean isValidIpAddress(String ipAddress) {
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))
			return false;

		return true;
	}
}
