package com.zhzx.uip.commons.exception;

public class NotLoginedException extends BusinessException {
	private static final long serialVersionUID = 7192445066024856707L;

	public NotLoginedException(String errorMessage) {
		super("ETS-5BP00001", errorMessage);
	}
}
