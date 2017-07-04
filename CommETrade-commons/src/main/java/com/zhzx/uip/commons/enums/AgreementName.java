package com.zhzx.uip.commons.enums;

public enum AgreementName {
	AGREEMENT("0","产品说明书"),
	RISK("1","风险揭示书"),
	CONTRACT("2","电子合同");

	private String type;
	private String description;

	private AgreementName(String category, String description){
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
	
}
