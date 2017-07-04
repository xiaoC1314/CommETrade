package com.zhzx.uip.commons.exception;

public class UserCenterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserCenterException() {
		super();
	}

	public UserCenterException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserCenterException(String message) {
		super(message);
	}

	public UserCenterException(Throwable cause) {
		super(cause);
	}
}
