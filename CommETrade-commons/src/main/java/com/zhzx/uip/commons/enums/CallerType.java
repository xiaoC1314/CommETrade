package com.zhzx.uip.commons.enums;

public enum CallerType {
	PC("api", "pc平台"), IPHONE("iphone ", "iPhone平台"),ANDROID("android","android平台"),GW("gw","WWW官网"),WX_APP("wxApp", "微信小程序");

	private String type;
	private String description;

	private CallerType(String type, String description) {
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
