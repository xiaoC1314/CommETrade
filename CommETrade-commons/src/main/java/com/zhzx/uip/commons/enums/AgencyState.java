package com.zhzx.uip.commons.enums;

public enum AgencyState {
	CREATE("0", "创建"), OPEN_QUERY("1", "开通网上查询"), CLOSE_QUERY("2", "关闭网上查询"),APPLY_CLOSE_QUERY("3", "申请关闭网上查询");

	private String state;
	private String description;

	private AgencyState(String state, String description) {
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
