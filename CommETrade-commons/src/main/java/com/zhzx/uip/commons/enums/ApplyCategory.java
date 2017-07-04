package com.zhzx.uip.commons.enums;

public enum ApplyCategory {
	DECLARE("0","申购"),
	SUBSCRIBE("1","认购"),
	REMMIT("2","赎回");

	private String category;
	private String description;

	private ApplyCategory(String category, String description){
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
