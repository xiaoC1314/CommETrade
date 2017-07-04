package com.zhzx.uip.commons.module;

import java.io.Serializable;

/**
 * 普通的入参
 * @author liming
 *
 * 目前先继承ValidatorForm，后期扩展其他接口如WebService，去掉该继承。
 */
public class GeneralPara implements Serializable {
	private static final long serialVersionUID = -8015527491830557481L;
	/**
	 * 商户号
	 */
	private String merid;
	/**
	 * 商户类型
	 */
	private String usertype;
	/**
	 * 签名方法
	 */
	private String signmode;
	/**
	 * 签名
	 */
	private String signmsg;
	/**
	 * 返回格式
	 */
	private String format;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 时间戳 格式:yyyymmddhh24MMss
	 */
	private String timestamp;
	/**
	 * Session KEY
	 */
	private String sessionkey;
	/**
	 * @return the merid
	 */
	private String function;
	private String channel;

	/**
	 * 扩展信息，内部调用，base64(json)
	 * 当前支持clientIp,serverIp,serverPort,userAgent
	 */
	private String meta;

	/** 客户自定义默认委托方式 */
	private String custtrust;



	public String getCusttrust() {
		return custtrust;
	}

	public void setCusttrust(String custtrust) {
		this.custtrust = custtrust;
	}

	public String getMerid() {
		return merid;
	}

	/**
	 * @param merid the merid to set
	 */
	public void setMerid(String merid) {
		this.merid = merid;
	}
	/**
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}
	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	/**
	 * @return the signmode
	 */
	public String getSignmode() {
		return signmode;
	}
	/**
	 * @param signmode the signmode to set
	 */
	public void setSignmode(String signmode) {
		this.signmode = signmode;
	}
	/**
	 * @return the signmsg
	 */
	public String getSignmsg() {
		return signmsg;
	}
	/**
	 * @param signmsg the signmsg to set
	 */
	public void setSignmsg(String signmsg) {
		this.signmsg = signmsg;
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the sessionkey
	 */
	public String getSessionkey() {
		return sessionkey;
	}
	/**
	 * @param sessionkey the sessionkey to set
	 */
	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}
	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}
	/**
	 * @param function the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}
}
