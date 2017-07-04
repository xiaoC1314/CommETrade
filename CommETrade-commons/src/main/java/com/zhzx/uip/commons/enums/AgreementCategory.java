package com.zhzx.uip.commons.enums;

public enum AgreementCategory {
	FOUND("0","与产品相关"),
	BASE("1","基础协议");

	private String category;
	private String description;
	
	private AgreementCategory(String category,String description){
		this.category = category;
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}
