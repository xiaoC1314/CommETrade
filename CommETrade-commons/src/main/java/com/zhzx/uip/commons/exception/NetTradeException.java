package com.zhzx.uip.commons.exception;

/**
 * API exception
 */
public class NetTradeException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -1267826311634066132L;
	String code;
	String message;


	public NetTradeException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
