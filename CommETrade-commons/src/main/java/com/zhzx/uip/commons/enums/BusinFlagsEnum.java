package com.zhzx.uip.commons.enums;

public enum BusinFlagsEnum {
	SUBSCRIBE("020", "认购"),
	DECLARE("022", "申购"),
	REDEEM("024", "赎回"),
	SWITCH("036", "转换"),
	BONUS("029", "设置分红方式"),
	REALTIMEREDEEM("098", "快速过户"),
	WITHDRAW("053", "撤销申请"),
	FIX_BANKMSP_MODIFY("993", "银行信息修改");

	private String businFlag;
	private String businDesc;
	BusinFlagsEnum(String businFlag, String businDesc) {
		this.businFlag = businFlag;
		this.businDesc = businDesc;
	}

	public String getBusinFlag() {
		return businFlag;
	}

	public void setBusinFlag(String businFlag) {
		this.businFlag = businFlag;
	}

	public String getBusinDesc() {
		return businDesc;
	}

	public void setBusinDesc(String businDesc) {
		this.businDesc = businDesc;
	}

}
