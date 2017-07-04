package com.zhzx.uip.commons.module;

public class CallerInfo {
	private String callerKey; // 应用唯一标识 X-App-Key 如iphone,android,api,,guanwang
	private String callerVersion; // X-App-Version 如1.1.5
	private String callerProfile; // X-App-Env 如release huidu
	private String callerType; // X-App-Type 如iphone,android,api,,guanwang

	public String getCallerKey() {
		return callerKey;
	}

	public void setCallerKey(String callerKey) {
		this.callerKey = callerKey;
	}

	public String getCallerVersion() {
		return callerVersion;
	}

	public void setCallerVersion(String callerVersion) {
		this.callerVersion = callerVersion;
	}

	public String getCallerProfile() {
		return callerProfile;
	}

	public void setCallerProfile(String callerProfile) {
		this.callerProfile = callerProfile;
	}

	public String getCallerType() {
		return callerType;
	}

	public void setCallerType(String callerType) {
		this.callerType = callerType;
	}

	@Override
	public String toString() {
		return "CallerInfo [" + (callerKey != null ? "callerKey=" + callerKey + ", " : "")
				+ (callerVersion != null ? "callerVersion=" + callerVersion + ", " : "")
				+ (callerProfile != null ? "callerProfile=" + callerProfile + ", " : "")
				+ (callerType != null ? "callerType=" + callerType : "") + "]";
	}
	

}
