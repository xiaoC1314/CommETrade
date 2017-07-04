package com.zhzx.uip.commons.constants;
public class Constants {
	/***************** SQL组装相关 **********************/
	public static final String IBATIS_SQL_PATH = "com.zhzx.agency.commons.module";
	public static final String IBATIS_OPT_POINT = ".";
	public static final String IBATIS_OPT_ADD_KEY = "add";
	public static final String IBATIS_OPT_UPDATE_KEY = "update";
	public static final String IBATIS_OPT_DELETE_KEY = "delete";
	public static final String IBATIS_OPT_LIST_KEY = "list";
	public static final String IBATIS_OPT_LISTCOUNT_KEY = "listcount";
	public static final String IBATIS_OPT_FIND_KEY = "find";
	public static final String IBATIS_OPT_ADD_VALUE = "insert{0}";
	public static final String IBATIS_OPT_UPDATE_VALUE = "update{0}";
	public static final String IBATIS_OPT_DELETE_VALUE = "delete{0}";
	public static final String IBATIS_OPT_LIST_VALUE = "list{0}";
	public static final String IBATIS_OPT_LISTCOUNT_VALUE = "list{0}Count";
	public static final String IBATIS_OPT_FIND_VALUE = "find{0}ByID";

	/***************** http方法 **********************/
	public static final String HTTP_REQUEST_METHOD_GET = "GET";
	public static final String HTTP_REQUEST_METHOD_POST = "POST";

	/***************** 手机验证码相关 **********************/
	public static final String MOBILE_CODE_PREFFIX = "agency_mobileCode";
	public static final String CODE_STR_SPLITE = "_";
	public static final Integer MOBILE_CODE_LENGTH = 6;
	public static final Long EXPIRE_TIME = 15 * 60 * 1000l;// 失效时间,15分钟
	public static final Long SMS_INTERVAL_TIME = 1 * 60 * 1000l;// 短信发送间隔时间,1分钟

	/***************** freemarker模板相关 **********************/
	public static final String FTL_BASE_DIR = "WEB-INF/ftl/";
	public static final String MOBILE_CODE_COMMON_FTL = "sms/common.ftl";

	/***************** 脱敏相关 **********************/
	public static final String MOHU_STR_4 = "****";

	/***************** 最大年龄 **********************/
	public static final Integer MAX_AGE = 999;

	/***************** 接口返回码相关 **********************/
	public static final String SUCCESS_CODE = "200";

	/***************** 开户相关 **********************/
	public static final String COMMUNICATION_ADDR_DEFAULT = "某省某市某县";
	public static final String EMAIL_DEFAULT = "default@default.com";
	public static final String INVEST_LIVE_DEFAULT = "1";
	public static final String NATIONALITY_DEFAULT = "156";
	public static final String RECKONING_MAIL_TYPE_DEFAULT = "2";
	public static final String RECKONING_SEND_TYPE_DEFAULT = "3";
	public static final String SEX_DEFAULT = "1";
	public static final String WORK_DEFAULT = "08";
	public static final String ZIP_CODE_DEFAULT = "000000";
	public static final String EMONEY_FUND_FLAG_DEFAULT = "2";
	public static final String ID_VALIDATE_DEFAULT = "20990101";
	public static final String BIRTHDAY_DEFAULT = "20990101";
	public static final String IS_VERIFY = "1";
	public static final String TRADEPASSWORD_DES_KEY = "9f509c818d447ba141464b41";// 齐鲁资管AFK

	/***************** 用户协议相关 **********************/
	public static final String AGREEMENT_VERSION_DEFAULT = "1.0";

	/***************** 银行卡相关 **********************/
	public static final String USER_BANK_NOT_VERIFIED = "0";
	public static final String USER_BANK_VERIFIED = "1";
	public static final String CAPITALMODE_SHOUFUJIE = "r";
	
	/***************** 风险问卷相关 **********************/
	public static final Integer QUESTION_LIMIT_MONTH = -6; // 当前时间往前6个月
	public static final Integer QUESTION_LIMIT_COUNT = 3; // 做风险问卷的次数不能超过3次


    /***************** 预约码使用次数 **********************/
    public static final String USER_RESERVE_LIMIT_PREFIX = "user_reserve_limit_";
    
    /***************** 数据字典相关 **********************/
    public static final String ROOT_CAPTION = "#";
    public static final String NETTARDE_DIC_KEY = "nettrade_dictionary";
    public static final String NETTARDE_DIC_ROOT_KEY = "nettrade_dictionary_root";
    public static final String DIC_NAME_LOGIN_SWITCH = "限制登录的方式";
    public static final String DIC_NAME_LOGIN_SWITCH_APP_VERSION = "根据APP版本号";
    public static final String DIC_NAME_LOGIN_SWITCH_APP_VERSION_ON = "1";
    public static final String DIC_NAME_APP_VERSION = "APP版本号";
    public static final String DIC_NAME_APP_VERSION_MIN = "限制登录的APP最低版本号";
    public static final String APP_VERSION_SPLITTER = ".";
    public static final String DIC_NAME_FUND_OPEN_CHANNEL = "产品开放渠道";
    public static final String DIC_NAME_FUND_OPEN_CHANNEL_LZG = "陆资管";
    
    /***************** 消息头相关　*********************/
	public static final String HEADER_APP_KEY = "X-App-Key";
	public static final String HEADER_APP_TYPE = "X-App-Type";
	public static final String HEADER_APP_VERSION = "X-App-Version";
	public static final String HEADER_APP_ENV = "X-App-Env";
	
	public static final String APP_KEY = "appKey";
	public static final String APP_TYPE = "appType";
	public static final String APP_VERSION = "appVersion";
	public static final String APP_ENV = "appEnv";
}
