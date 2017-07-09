package com.zhzx.uip.commons.module;

import com.zhzx.uip.commons.enums.ErrorEnum;

import java.io.Serializable;

public class ResponseVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean success = false;
	private String code;
	private Object data;
	private Integer recordsize;
	private String message;

	public ResponseVo(){
		
	}
	
	public ResponseVo(String message) {
		this.message = message;
	}

	public ResponseVo(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public ResponseVo(Boolean success, String message,String code) {
		this.success = success;
		this.message = message;
		this.code = code;
	}

	public ResponseVo(Boolean success, String message, String code, Object data) {
		this.success = success;
		this.message = message;
		this.code = code;
		this.data = data;
	}

	public ResponseVo(Boolean success, String code, String message, Object data,Integer recordSize) {
		this.success = success;
		this.message = message;
		this.code = code;
		this.data = data;
		this.recordsize = recordSize;
	}

	public ResponseVo(Boolean success, ErrorEnum errorEnum, Object data) {
		this.success = success;
		this.message = errorEnum.getErrorMsg();
		this.code = errorEnum.getErrorCode();
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getRecordsize() {
		return recordsize;
	}

	public void setRecordsize(Integer recordsize) {
		this.recordsize = recordsize;
	}

	@Override
	public String toString() {
		return "ResponseVo [success=" + success + ", code=" + code + ", data=" + data + ", message=" + message + "]";
	}

}
