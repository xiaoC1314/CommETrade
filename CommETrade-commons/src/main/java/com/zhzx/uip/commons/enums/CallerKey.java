package com.zhzx.uip.commons.enums;

import org.apache.commons.lang.StringUtils;

public enum CallerKey {
	PC("api", "66ca39fc126f5f2cea5c2347ec60cad2", "pc平台"),
	IPHONE("iphone", "9cb518e9147ed3e2a9f3dd191cc8a082","iPhone平台"),
	ANDROID("android", "d760170181d1e3c2af610b202a588701", "android平台"),
	GW("gw","a6ffb28e8634f3e80ffbf0075bc64aed","WWW官网"),
	AGENCY("agency", "84f5aaa59359df70534dbf36abad9bd1", "机构查询版"),
	WX_APP("wxApp", "946f45ca10cabbcd329941ab8040152e", "微信小程序");

	private String key;
	private String secretKey;
	private String description;

	private CallerKey(String key, String secretKey, String description) {
		this.key = key;
		this.secretKey = secretKey;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	public static CallerKey getEnumByKey(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		
		for (CallerKey callerKey : CallerKey.values()) {
			if (callerKey.getKey().equals(key)) {
				return callerKey;
			}
		}
		
		return null;
	}

}
