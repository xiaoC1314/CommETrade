package com.zhzx.uip.commons.utils;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhzx.uip.commons.module.CallerInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 用户系统的单点登陆 过滤,当用户量上去后，需要对memcache 进行 集群式
 *
 */
public class SSOManage {

	public static final Logger logger = LoggerFactory.getLogger(SSOManage.class);

	public static final String LOGIN_FAIL_TIMES = "login_fail_times_";

	public static final String EMAIL_SEPARATOR = "@";

	private static CacheTimes DEFAULT_CACHE_TIME = CacheTimes.TWENTY_MINUTES;
	private static int COOKIE_EXP = CookieUtils.AUTO_LOGIN_COOKIE_EXP;

	private static String qlzqzgDomain;

	static {
		try {
			Properties props = PropertiesLoaderUtils.loadAllProperties("config-common.properties");
			qlzqzgDomain = props.getProperty("qlzqzg.domain");
		} catch (IOException e) {

		}
	}

	public static String getCallerType() {
		String callerType = CallerType.PC.getType();
		CallerInfo callerInfo = getCallerInfo();
		if (null != callerInfo && StringUtils.isNotBlank(callerInfo.getCallerType())
				&& !CallerType.GW.getType().equals(callerInfo.getCallerKey())) {
			callerType = callerInfo.getCallerType();
		}
		return callerType;
	}

	public static CallerInfo getCallerInfo() {
		if (null == RequestContextHolder.getRequestAttributes())
			return null;

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		if (null == request) {
			return null;
		}

		String callerType = request.getHeader(Constants.HEADER_APP_TYPE);
		String callerKey = request.getHeader(Constants.HEADER_APP_KEY);
		if (StringUtils.isBlank(callerKey)) {
			callerKey = callerType;
		}
		String callerVersion = request.getHeader(Constants.HEADER_APP_VERSION);
		String callerProfile = request.getHeader(Constants.HEADER_APP_ENV);

		CallerInfo callerInfo = new CallerInfo();
		callerInfo.setCallerKey(callerKey);
		callerInfo.setCallerVersion(callerVersion);
		callerInfo.setCallerProfile(callerProfile);
		callerInfo.setCallerType(callerType);
		return callerInfo;
	}

	public static boolean isValidCaller() {
		CallerInfo callerInfo = getCallerInfo();
		if (null == callerInfo) {
			return false;
		}

		String callerKey = callerInfo.getCallerKey();
		String callerVersion = callerInfo.getCallerVersion();
		String callerProfile = callerInfo.getCallerProfile();
		String callerType = callerInfo.getCallerType();

		if (StringUtils.isBlank(callerKey) || StringUtils.isBlank(callerVersion) || StringUtils.isBlank(callerProfile)
				|| StringUtils.isBlank(callerType)) {
			return false;
		}

		return true;
	}
	
	private static String getAgencyUserStatusKeyPrefix() {
		String callerType = getCallerType();
		String userStatusKeyPrefix = UserCenterConstants.AGENCY_USER_STATUS_PREFIX + UserCenterConstants.KEY_SPLITTER + callerType
				+ UserCenterConstants.KEY_SPLITTER;
		return userStatusKeyPrefix;
	}

	private static String getAgencyUserKeyPrefix() {
//		String callerType = getCallerType();
//		String userKeyPrefix = UserCenterConstants.AGENCY_USER_PREFIX + UserCenterConstants.KEY_SPLITTER + callerType
//				+ UserCenterConstants.KEY_SPLITTER;
		String userKeyPrefix = UserCenterConstants.AGENCY_USER_PREFIX + UserCenterConstants.KEY_SPLITTER;
		return userKeyPrefix;
	}


}
