package com.zhzx.uip.commons.constants;

public interface UserCenterConstants {

	static String STATUS_OK = "true";
	static String STATUS_OFF = "false";
	static String PERSONAL_USER_PREFIX = "personal_user_";
	static String PERSONAL_USER_STATUS_PREFIX = "personal_user_status_";
	static String AGENCY_USER_PREFIX = "agency_user_";
	static String AGENCY_USER_STATUS_PREFIX = "agency_user_status_";
	static String KEY_SPLITTER = "#";
	
	static final String VALIDATE_CODE_SERIAL = "agency_validate_code_serial";
	
	static final String FIRST_LOGIN_SERIAL = "agency_first_login_serial";
	
	static final String LOGIN_NUMBER = "agency_login_number_";

	static final int LOGIN_NUMBER_MAX = 1;

	static final String PASSWORD_ERROR_COUNT = "agency_password_error_count_";
	
	static final int PASSWORD_ERROR_COUNT_MAX = 5;
	
	static final Integer LOCK_HOUR = 1;
	
	interface Cookie {
		
		// 首次登陆serial
		static String FIRST_LOGIN_SERIAL = "agency_first_login_serial";
		// 首次登陆mobile
		static String FIRST_LOGIN_MOBILE = "agency_first_login_mobile";
		// SSO用到的用户id,code长度为32
		static String USER_ID_COOKIE = "agency_userId";
		// SSO用到的用户名称
		static String USER_NAME_COOKIE = "agency_name";
		// SSO用到的昵称名称
		static String NICK_NAME_COOKIE = "agency_nickname";
		// SSO用到的登录状态
		static String LOGIN_FLAG_COOKIE = "agency_login";
		// SSO 用户图片
		static String LOGIN_USER_IMG = "agency_avatar";
		// SSO用到的email
		static String USER_EMAIL_COOKIE = "agency_email";
		// 显示需要的名称
		static String USER_SHOW_NAME_COOKIE = "agency_showname";
		// SSO用到的安全级别
		static String USER_LEVEL_COOKIE = "agency_level";

		static String USER_UUID_COOKIE = "agency_uuid";

		static String USER_TYPE_COOKIE = "agency_type";

//		static String HS_SESSION_KEY = "agency_hsSessionKey";
		
		static String MOBILE_COOKIE = "mobile";
		
		// 单位 s
		static int HS_SESSION_KEY_COOKIE_TIME = 1 * 60 * 20;

	}

	interface UserSession {
		// session 用户存储信息,id
		static String USER_OBJECT_SESSION_KEY = "agency_user_object_session_key_";
		// 用户对象session信息状态
		static String USER_OBJECT_SESSION_STATUS = "agency_user_obejct_session_status_";

		static String USER_SESSION_STATUS_OK = "agency_user_session_ok";

		static String USER_SESSION_STATUS_OFF = "agency_user_session_off";

		static String USER_SESSION_TYPE = "agency_user_session_type";
	}

}
