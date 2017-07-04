package com.zhzx.uip.commons.enums;

import org.apache.commons.lang.StringUtils;

public enum RegisterSource {
	PC("api","api"),
	ANDROID("android","android"),
	IPHONE("iphone","iphone"),
	LZG("lzg","陆资管");

	private String type;
	private String description;

	private RegisterSource(String category, String description){
		this.type = category;
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
	
	public static boolean isValid(String registerSource) {
		if (StringUtils.isBlank(registerSource))
			return false;
		
		boolean isValid = false;
		for (RegisterSource registerSourceEnum : RegisterSource.values()){
			if (registerSource.equals(registerSourceEnum.getType())) {
				isValid = true;
				break;
			}
		}
		
		return isValid;		
	}
	
}
