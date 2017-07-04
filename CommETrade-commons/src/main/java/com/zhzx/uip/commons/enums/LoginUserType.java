package com.zhzx.uip.commons.enums;

public enum LoginUserType {
	NOT_LOGIN("notLogin","未登录"),PERSONAL("p", "个人用户"), AGENCY("o", "机构用户经办人"),BOTH("p,o","同时登录"),UNKNOWN("unknown","未知异常");

	private String type;
	private String description;

	private LoginUserType(String type, String description) {
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
