package com.zhzx.uip.commons.constants;

/**
 * 项目公共Session的Attribute定义
 * 
 * @author wqhui
 * 
 */
public class MemChachedAttributes {
	/**
	 * sessionKey，在API端的会话标志
	 *
	 */
	public static final String SESSION_KEY = "sessionKey";
	/**
	 * sessionKey，在API端的会话标志
	 *
	 */
	public static final String LOGIN_KEY = "loginKEY";
    /**
     * 客户信息
     */
    public static final String CUST_INFO = "custinfo";
    
    /**
     * 角色信息
     */
    public static final String ROLE_INFO = "roleinfo";

    /**
     * key分隔符
     */
    public static final String KEY_SPLITTER = "_";
    
	/**
	 * 开户填写的手机号码
	 */
	public static final String OPENACCO_MOBILE = "openacco_mobile";
    /**
     * 银行签约手机号码
     */
	public static final String BANK_VERIFY_MOBILE = "bank_verify_mobile";

	/**
	 * 开户后自动登录等使用的交易密码
	 */
	public static final String AUTO_LOGIN_PASSWORD = "login_AutoLoginPwd";

	/**
	 * 登录密码明文
	 */
	public static final String LOGIN_PLAIN_PASSWORD = "login_LoginPlainPwd";

	/**
	 * 网银验证请求序列号
	 */
	public static final String ACCO_REQ_SERIAL = "bank_accoreqserial";

	/**
	 * 统一验证返回数据
	 */
	public static final String COMMON_VERIFY_RESULT = "bank_CommonVerifyResult";

	/**
	 * 找回密码获取到的客户编号
	 */
	public static final String CUSTNO_IN_RESET_PASSWORD = "resetpwd_CustNo";

	/**
	 * 找回密码获取到的手机号码
	 */
	public static final String MOBILE_IN_RESET_PASSWORD = "resetpwd_Mobile";

	/**
	 * 手机验证码已校验成功标记
	 */
	public static final String MOBILE_AUTHCODE_VALIDATED = "mobileauthcode_authcodeVerfied";

	/**
	 * 不做弱密码校验标志
	 */
	public static final String NOT_CHECK_WEAK_PASSWORD = "validation_NotCheckWeakPwd";

	/**
	 * 在线支付参数
	 */
	public static final String ONLINE_PAY_PARAMS = "pay_onlinePayParams";

    /**
     * 交易前鉴权
     */
    public static final String ONLINE_VALIDATA_BEFOREPAY = "validata_onlineBeforePay";

    /**
     * 鉴权成功标致
     */
    public static final String ONLINE_VALIDATA_RESULT_FLAG = "validata_result_flag";

	/**
	 * 最后一次支付的申请编号
	 */
	public static final String LAST_PAY_SERIAL = "pay_lastApplySerial";

    /**
	 * 第三方快速购买
	 */
	public static final String THIRDPART_DECLARE = "thirdpart_Declare";

    /**
     * 第三方快速购买金额
     */
    public static final String THIRDPART_DECLARE_APPLYSUM = "thirdpart_Declare_applysum";

    /**
     * 第三方快速赎回金额
     */
    public static final String THIRDPART_REDEEEM_APPLYSUM = "thirdpart_Redeem_applysum";

  /*********************************************首页缓存数据区域*************************/
    /**
     * 首页姓名
     */
    public static final String CUSTNAME= "cust_name";

    /**
     * 当前工作日
     */
    public static final String CURRENT_WORK_DATE= "Current_Work_Ddate";
    /**
     * 上一工作日
     */
    public static final String LAST_WORK_DATE= "Last_Work_Ddate";

    /**
     * 下一工作日
     */
    public static final String NEXT_WORK_DATE= "Next_Work_Date";

    /**
     * 首页直销基金列表
     */
    public static final String MY_DSFUND_WIDGET= "My_DSFund_Widget";
    /**
     * 所有的盈亏数据
     */
    public static final String TOTAL_INCOME_ANALYSIS= "IncomeAnalysis_total";
    /**
     * 近30天的盈亏数据
     */
    public static final String MONTH_INCOME_ANALYSIS= "IncomeAnalysis_month";
    /**
     * 昨日的盈亏数据
     */
    public static final String DAY_INCOME_ANALYSIS= "IncomeAnalysis_day";

    /**
     * 首页饼图js
     */
    public static final String SHARE_DATA_JS= "shareDatajs";
    /**
     * 历史收益js
     */
    public static final String BY_TOTAL_JS= "byTotaljs";
    /**
     * 昨日收益js
     */
    public static final String BY_DATE_JS= "byDateJs";
    /**
     * 30天以来的收益
     */
    public static final String BY_30DATE_JS= "by30Dayjs";
    /**
     * 首页推荐（大家正在买）
     */
    public static final String FUND_UP= "FundUp";

    /**
     * 首页温馨提示，暂不修改，以session过期为一个阶段   值为1：暂时不修改
     */
    public static final String TEMPORARILY_NOT_MODIFY= "Temporarily_not_modify";

    /**
     * 持有的基金。
     */
    public static final String MY_FUNDS_MAIN= "myFundsMain";

    /**
     * 基金产品信息。
     */
    public static final String MYFUNDINFO= "myfundinfo";

    /*********************************************首页缓存数据区域*************************/
    
    public static final String RESET_TRADE_PWD_PREFFIX = "reset_trade_pwd_preffix_";
}
