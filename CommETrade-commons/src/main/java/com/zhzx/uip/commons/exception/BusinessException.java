package com.zhzx.uip.commons.exception;

import org.apache.commons.lang.StringEscapeUtils;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorCode = null;

	private String[] variables = null;

	public BusinessException(String errorCode) {
		this.errorCode = errorCode;
	}

	public BusinessException(String errorCode, String[] variables) {
		this.errorCode = errorCode;
		this.variables = (variables == null ? null : (String[]) variables.clone());
	}

	public BusinessException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public BusinessException(String errorCode, String errorMessage, String[] variables) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.variables = (variables == null ? null : (String[]) variables.clone());
	}

	public BusinessException(String errorCode, Throwable t) {
		super(t);
		this.errorCode = errorCode;
	}

	public BusinessException(String errorCode, String errorMessage, Throwable t) {
		super(errorMessage, t);
		this.errorCode = errorCode;
	}

	public BusinessException(String errorCode, String[] variables, Throwable t) {
		super(t);
		this.errorCode = errorCode;
		this.variables = (variables == null ? null : (String[]) variables.clone());
	}

	public BusinessException(String errorCode, String errorMessage, String[] variables, Throwable t) {
		super(errorMessage, t);
		this.errorCode = errorCode;
		this.variables = (variables == null ? null : (String[]) variables.clone());
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String[] getVariable() {
		if (this.variables == null) {
			return null;
		}

		String[] escapedVariable = new String[this.variables.length];
		for (int i = 0; i < this.variables.length; i++) {
			escapedVariable[i] = StringEscapeUtils.escapeHtml(this.variables[i]);
		}
		return escapedVariable;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("错误代码[").append(this.errorCode).append("]\n");
		if ((this.variables != null) && (this.variables.length != 0)) {
			sb.append("绑定变量[");
			for (int i = 0; i < this.variables.length; i++) {
				sb.append(this.variables[i]);
			}
			sb.append("]\n");
		}
		sb.append(super.toString());
		return sb.toString();
	}
}
