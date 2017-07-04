package com.zhzx.uip.commons.constants;

/**
 * 
 * @author zhangtingrui
 * NT:netTrade
 * 0开头: 通用
 * 1开头：账户类
 * 2开头：产品类
 * 3开头：预约码相关
 * 4开头：交易类
 * 5开头：银行卡类
 * 7开头：查询类
 */
public class AgencyErrorCodes {
	/** 通用错误码 **/
	public static final String SUCCESS = "A-0000"; // 处理成功
	public static final String NOT_LOGIN = "A-0001";	// 未登陆
	public static final String VALIDATION_FAILED = "A-0002"; // 请求参数校验失败
	public static final String CACHE_FAILED = "A-0003"; // 操作缓存失败
	public static final String EMPTY_DATA = "A-0004"; // 操作缓存失败
	public static final String SIGN_FAILD = "A-0005"; // 验签失败或连接超时
	public static final String SYSERROR = "A-9998"; // 系统异常
	public static final String UNKNOWN = "A-9999"; // 未知
	
	
	/** 账户类错误码 **/
	public static final String LOGIN_NEED_CODE = "A-1001";	// 登陆需要图片验证码
	public static final String LOGIN_CODE_ERROR = "A-1002";// 登陆图片验证码不匹配
	public static final String USER_NAME_USED = "A-1003";// 用户名已注册
	public static final String USER_NOT_EXIST = "A-1004";// 用户不存在
	public static final String PASSWORD_ERROR = "A-1005";// 密码错误
	public static final String PASSWORD_ERROR_MAX = "A-1006";// 密码输错次数过多,锁定账户
	public static final String USER_LOCKED = "A-1007";// 账户已锁定
	public static final String AGENCY_EMPTY = "A-1008";// 经办人的机构信息为空 
	public static final String AGENCY_NOT_AUTHED = "A-1009";// 经办人没权操作该机构
	public static final String OPERATOR_NOT_FIRST_LOGIN = "A-1010";// 经办人不是首次登录
	public static final String OPERATOR_FIRST_LOGIN = "A-1011";// 经办人首次登录
	public static final String OPERATOR_FIRST_LOGIN_EMPTY = "A-1012";// 首次登录用户信息为空
	public static final String EXIST_NOT_SIGN_AGENCY = "A-1013";// 有未签署协议的机构
	public static final String NOT_EXIST_NOT_SIGN_AGENCY = "A-1014";// 没有未签署协议的机构
	public static final String NOT_VALID_AGENCY = "A-1015";// 以下机构已签署协议或没权操作
	public static final String SIGNED_AGENCY_EMPTY = "A-1016";// 经办人已签过协议的机构信息为空
	public static final String LEAST_ONE_SIGNED_AGENCY = "A-1017";// 需要至少签署一个机构账户的协议
	
	public static final String HS_LOGIN_FAILED = "NT-1008";// 调用恒生登录接口失败
	public static final String LOGIN_ACCOUNT_OPENED = "login_account_opened";//已开户,去引导页注册
	public static final String LOGIN_ACCOUNT_NOT_NETCONSIGN = "login_account_not_netconsign";//需开通网上交易
	public static final String MOBILE_CODE_EMPTY = "NT-1011";// 未发送验证码
	public static final String MOBILE_CODE_ERROR = "NT-1012";// 验证码错误
	public static final String MOBILE_CODE_EXPIRE_TIME_EMPTY = "NT-1013";// 验证码失效时间
	public static final String MOBILE_CODE_EXPIRED = "NT-1014";// 验证码已失效
	public static final String SERIAL_EMPTY = "NT-1015";// serial为空
	public static final String HS_GET_CUSTINFO_FAILED = "NT-1016";// 获取客户资料失败
	public static final String HS_MODIFY_CUSTINFO_FAILED = "NT-1017";// 调用恒生接口修改客户资料失败
	public static final String OLD_PASSWORD_ERROR = "NT-1018";// 旧密码错误
	public static final String CUST_NOT_EXIST = "NT-1019";// 客户不存在
	public static final String OPEN_ACCOUNT_TRACE_NOT_EXIST = "NT-1021";// 开户留痕不存在
	public static final String OPEN_ACCOUNT_TRACE_STATUS_ERROR = "NT-1022";// 开户流程状态不正确
	public static final String WEAK_PASSWORD_CHECK_FAILED = "NT-1023";// 弱密码校验失败
	public static final String QUERY_ACCO_STATE_FAILED = "NT-1024";// 调用恒生接口查询交易账号失败
	public static final String APPLY_ACCO_FAILED = "NT-1025";// 开通网上交易失败
	public static final String OPEN_ACCO_FAILED = "NT-1026";// 开户失败
	public static final String RESET_TRADE_PASSWORD_FAILED = "NT-1027";// 重置交易密码失败
	public static final String TRADE_PASSWORD_ERROR = "NT-1028";// 交易密码错误
	public static final String MOBILE_CODE_SEND_OVERRUN = "NT-1029";// 短信发送超限
	public static final String MOBILE_CODE_GENERATED_FAILED = "NT-1030";// 根据模板生成信内容生成失败
	public static final String MOBILE_CODE_SEND_FAILED = "NT-1031"; // 短信发送失败
	public static final String GET_RISK_QANDA_FAILED = "NT-1032";// 获取风险问卷失败
	public static final String SUBMIT_RISK_TOO_MANY_TIMES = "NT-1033";// 问卷调查次数过多
	public static final String SUBMIT_RISK_QANDA_FAILED = "NT-1034"; // 提交风险问卷失败
	public static final String LOGIN_BLACK_LIST = "NT-1035"; // 登录黑名单
	public static final String LOGIN_APP_VERSION_TOO_LOW = "NT-1036"; // APP版本过低，限制登录
	
	/** 产品类错误码 **/

    /** 预约码类错误码 **/

	
	/** 交易类错误码 **/
	
	
	/** 银行卡类错误码 **/

	
    /**交易查询类错误码**/
  
}
