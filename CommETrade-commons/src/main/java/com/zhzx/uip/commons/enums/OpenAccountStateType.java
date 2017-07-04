package com.zhzx.uip.commons.enums;

import org.apache.commons.lang.StringUtils;

public enum OpenAccountStateType {
	NEW("0", "待实名认证"), BANK_VERIFY_FAILED("1", "银行卡验证失败"), BANK_VERIFY_SUCCESS("2", "银行卡验证成功"), MOBILE_CHECK_FAILED("3",
			"手机验证失败"), MOBILE_CHECK_SUCCESS("4", "手机验证成功"), RISK_FAILED("5", "风险评测失败"), RISK_SUCCESS("6",
					"风险评测成功"), OPEN_ACCOUNT_FAILED("7", "开户失败"), OPEN_ACCOUNT_SUCCESS("8", "开户成功");

	private String state;
	private String description;
	private static OpenAccountStateType[] notSubmitRisk = { OpenAccountStateType.NEW,
			OpenAccountStateType.BANK_VERIFY_FAILED, OpenAccountStateType.BANK_VERIFY_SUCCESS,
			OpenAccountStateType.MOBILE_CHECK_FAILED };
	
	private static OpenAccountStateType[] notMobileCheck = { OpenAccountStateType.NEW,OpenAccountStateType.BANK_VERIFY_FAILED };

	private OpenAccountStateType(String state, String description) {
		this.state = state;
		this.description = description;
	}

	public static boolean canMobileCheck(String state) {
		if (StringUtils.isBlank(state))
			return false;

		for (OpenAccountStateType typeEnum : notMobileCheck) {
			if (typeEnum.getState().equals(state)) {
				return false;
			}
		}

		return isValidState(state);
	}
	
	public static boolean canSubmitRisk(String state) {
		if (StringUtils.isBlank(state))
			return false;

		for (OpenAccountStateType typeEnum : notSubmitRisk) {
			if (typeEnum.getState().equals(state)) {
				return false;
			}
		}

		return isValidState(state);
	}

	public static boolean canSubmitOpenAccount(String state) {
		if (StringUtils.isBlank(state))
			return false;

		if (OpenAccountStateType.RISK_SUCCESS.getState().equals(state)
				|| OpenAccountStateType.OPEN_ACCOUNT_FAILED.getState().equals(state))
			return true;

		return false;
	}

	public static boolean isValidState(String state) {
		if (StringUtils.isBlank(state))
			return false;

		for (OpenAccountStateType typeEnum : OpenAccountStateType.values()) {
			if (state.equals(typeEnum.getState())) {
				return true;
			}
		}

		return false;
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
