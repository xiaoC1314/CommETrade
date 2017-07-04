package com.zhzx.uip.commons.enums;

import org.apache.commons.lang.StringUtils;

public enum MobileCodeType {

	FIRST_LOGIN("firstLogin"),
	FINDPWD("findPwd");

	private String type;

	private MobileCodeType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static boolean isValidMobileCodeType(String type) {
		if (StringUtils.isBlank(type))
			return false;

		for (MobileCodeType mobileCodeType : MobileCodeType.values()) {
			if (mobileCodeType.getType().equals(type))
				return true;
		}

		return false;
	}
}
