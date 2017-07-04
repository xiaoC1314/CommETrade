package com.zhzx.uip.commons.constants;

public class HSErrorCodes {

	public static final String SUCCESS = "ETS-5BP0000";
	public static final String UNKNOWN = "ETS-5BP9999";
	public static final String SESSION_EXPIRED = "ETS-5BP0001";
	public static final String ROLE_PERMISSION_DENIED = "ETS-5BP0002";
	public static final String CSRF_ATTACK = "ETS-5BP0003";
	public static final String SCRIPT_INJURE = "ETS-5BP0004";
	public static final String FORM_VALIDTE_FAILED = "ETS-5BP0005";
	public static final String AGREEMETN_EXIST = "ETS-5BD0005";

	/**
	 * 通用错误（非hs标准返回错误）
	 */
	public static final String COMMONERROR = "ETS-99999999";

	/**
	 * 验证码错误
	 */
	public static final String AUTHCODE_INVALID = "ETS-5BA00000";

	/**
	 * 无有效的账户信息
	 */
	public static final String ACCO_STATE_INVALID = "ETS-5BA00001";

	/**
	 * 客户未开通网上交易
	 */
	public static final String CUST_NOT_NET_OPEN = "ETS-5BA00002";

	/**
	 * 网银身份验证信息丢失
	 */
	public static final String BANK_BACK_INFO_LOST = "ETS-5BA00003";

	/**
	 * 网银身份验证数据无效
	 */
	public static final String BANK_REQUEST_PARAM_INVALID = "ETS-5BA00004";




	/**
	 * 客户已开通网上交易
	 */
	public static final String CUST_NET_OPENED = "ETS-5BA00005";

	/**
	 * 未满18岁不允许开户
	 */
	public static final String AGE_18_LIMIT = "ETS-5BA00006";

	/**
	 * 两次输入密码不一致
	 */
	public static final String PASSWORD_MISMATCH = "ETS-5BA00007";

	/**
	 * 找回密码关键信息丢失
	 */
	public static final String RESET_PASSWORD_ACCOINFO_LOST = "ETS-5BA00008";

	/**
	 * 开户类型不支持
	 */
	public static final String NOT_SUPPORT_BANK_BUSIN = "ETS-5BA00009";

	/**
	 * 手机验证码为空
	 */
	public static final String VERIFY_MOBILE_IS_EMPTY = "ETS-5BA00010";

	/**
	 * 客户未开通网上交易、或者提交资料不匹配（ 密码找回）
	 */
	public static final String INFO_NOT_MATCH_OR_NOT_OPEN_NET = "ETS-5BA00011";

	/**
	 * 手机验证码未校验成功
	 */
	public static final String MOBILE_AUTHCODE_NOT_VERIFY = "ETS-5BA00012";

	/**
	 * 未绑定手机或者号码不匹配
	 * 
	 */
	public static final String NOT_BIND_MOBILE_OR_MOBILE_NOT_MATCH = "ETS-5BA00013";

	/**
	 * 机构不支持一键开户
	 */
	public static final String ORG_CAN_NOT_ONE_KEY_OPEN_ACCO = "ETS-5BA00014";


    public static final String CCB_SIGN_FAILED= "ETS-5BA00015";


	/**
	 * 找不到查询记录
	 */
	public static final String NO_RECORD = "ETS-5BQ00000";

	/**
	 * 订单已失效
	 */
	public static final String INVALID_ORDER = "ETS-5BT00000";

	/**
	 * 无对应申请数据
	 */
	public static final String NO_SUCH_REQUEST = "ETS-5BT00001";

	/**
	 * 支付失败
	 */
	public static final String PAY_FAILED = "ETS-5BT00002";

	/**
	 * 赎回失败
	 */
	public static final String REDEEM_FAILED = "ETS-5BT00003";

	/**
	 * 
	 */
	public static final String CARD_NOT_EXIST = "ETS-5BT00004";

	/**
	 * 修改分红失败
	 */
	public static final String BONUS_FAILED = "ETS-5BT00005";

	/**
	 * 转换失败
	 */
	public static final String TRANS_FAILED = "ETS-5BT00006";

	/**
	 * 转换失败
	 */
	public static final String ENCASH_FAILED = "ETS-5BT00007";
	
	
	/**
	 * 无可定投申购基金
	 * 
	 */
	public static final String NO_FIX_DECLARE_FUND = "ETS-5BF00001";
	
	/**
	 * 电子合同、风险揭示书文件不存在错误
	 * 
	 */
	public static final String FILE_NOT_EXIST = "ETS-5BS00001";
	
	/**
	 * 预约截止日期不存在
	 * 
	 */
	public static final String HOPEDATE_NOT_EXIST = "ETS-5BS00002";
	/**
	 * 银行卡找回密码时，填写的手机号码和数据库中存的不一致
	 * 
	 */
	public static final String MOBILE_NO_SAME = "ETS-5BA00020";

	/**
	 * 交易完成前请确保完成鉴权
	 *
	 */
	public static final String VERIFY_BEFORE_PAY_ERROR = "ETS-5BA00021";

	/**
	 * 未登录
	 */
	public static final String NOT_LOGIN_EEROR = "ETS-NOT-LOGIN";
}
