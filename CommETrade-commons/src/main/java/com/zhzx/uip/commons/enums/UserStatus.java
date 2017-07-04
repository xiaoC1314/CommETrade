package com.zhzx.uip.commons.enums;

public enum UserStatus {
	CREATE(1, "首次登陆"), RESET_PWD(2, "重置密码"), LOCK(3, "锁定"), NORMAL(4, "正常"), CLOSE(5, "关闭");

	private Integer status;
	private String description;

	private UserStatus(Integer status, String description) {
		this.status = status;
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
