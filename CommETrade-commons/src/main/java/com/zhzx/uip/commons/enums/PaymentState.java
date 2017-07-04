package com.zhzx.uip.commons.enums;

public enum PaymentState {
	UNKNOW("5", "未知"), SUCCESS("6", "成功"), FAILED("7", "失败"),OTHER("OTHER", "其他");

	private String state;
	private String description;

	private PaymentState(String state, String description) {
		this.state = state;
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
