package com.zhzx.uip.commons.enums;

public enum ErrorEnum {
/********************************全局错误信息*****************************************/
	COMM_SUCCESS("UIP_COMM_SUCCESS", "操作成功"),
	COMM_ERROR("UIP_COMM_ERROR", "系统错误"),
	UIP_COMM_PARAM_ERROR("UIP_COMM_PARAM_ERROR", "入参有误"),
    UIP_COMM_NO_AUTH_ERROR("UIP_COMM_NO_AUTH_ERROR", "没有访问权限"),
	COMM_EMPTY_DATA("UIP_COMM_EMPTY_DATA", "空数据"),
/********************************汇款交易查询错误信息*****************************************/
	COMM_DATE_FORMAT_ERR("UIP_COMM_DATE_FORMAT_ERR_0001", "日期参数格式错误"),
	COMM_DATE_NO_BLANK_ERR("UIP_COMM_NO_BLANK_ERR_0001", "日期参数不能为空"),
	COMM_PARAM_NO_FUNDCODE_ERR("COMM_PARAM_NO_FUNDCODE_ERR_0001", "产品代码不能为空"), ;


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
