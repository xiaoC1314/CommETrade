package com.zhzx.uip.commons.enums;

public enum OperationType {
	LOGIN("login","用户登录"),
	FIRST_LOGIN("first_login","用户首次登录重置密码和签署协议"),
	SIGN_AGREEMENTS("sign_agreements","机构投资者网上交易查询版本用户协议"),
	LOGIN_BLACK("login_black","黑名单用户登录"),
	CHANGEPWD("changePwd","修改密码"),
	FINDPWD("findPwd","找回密码"),
	LOGOUT("logout","用户退出"),
	CLOSE_QUERY("close_query","关闭网上查询");
	
	private String type;
	private String description;
	
	private OperationType(String type,String description){
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
