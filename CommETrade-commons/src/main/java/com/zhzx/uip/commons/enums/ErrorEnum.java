package com.zhzx.uip.commons.enums;

public enum ErrorEnum {
/********************************全局错误信息*****************************************/
	COMM_SUCCESS("UIP_COMM_SUCCESS", "操作成功"),
	COMM_ERROR("UIP_COMM_ERROR", "系统错误"),
	UIP_COMM_PARAM_ERROR("UIP_COMM_PARAM_ERROR", "入参有误"),
    UIP_COMM_NO_AUTH_ERROR("UIP_COMM_NO_AUTH_ERROR", "没有访问权限"),
	COMM_EMPTY_DATA("UIP_COMM_EMPTY_DATA", "空数据"),
/********************************查询错误信息*****************************************/
	COMM_DATE_FORMAT_ERR("UIP_COMM_DATE_FORMAT_ERR_0001", "日期参数格式错误"),
	COMM_DATE_NO_BLANK_ERR("UIP_COMM_NO_BLANK_ERR_0001", "日期参数不能为空"),
	COMM_PARAM_NO_FUNDCODE_ERR("COMM_PARAM_NO_FUNDCODE_ERR_0001", "产品代码不能为空"),
	COMM_REGISTER_FAIL_ERR("COMM_REGISTER_FAIL_ERR", "用户注册失败"),
	COMM_USER_NOT_EXIST_ERR("COMM_USER_NOT_EXIST_ERR", "用户不存在"),
	COMM_PASSWORD_INCORRECT_ERR("COMM_PASSWORD_INCORRECT_ERR", "用户密码不正确"),
	COMM_PROD_NOT_EXIST_ERR("COMM_PROD_NOT_EXIST_ERR", "商品不存在"),
	COMM_CART_IS_NULL_ERR("COMM_CART_IS_NULL_ERR", "购物车为空"),
	COMM_PHONE_EXIST_ERR("COMM_PHONE_EXIST_ERR", "手机号码已注册"), ;

	private String errorCode;
	private String errorMsg;
	ErrorEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
