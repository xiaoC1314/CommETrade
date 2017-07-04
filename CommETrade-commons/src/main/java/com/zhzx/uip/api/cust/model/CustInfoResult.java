package com.zhzx.uip.api.cust.model;

import com.zhzx.uip.commons.module.GeneralResult;

import java.util.List;

/**
 * @author xiaoC
 * @since 1.0.0
 */
public class CustInfoResult extends GeneralResult {
	private static final long serialVersionUID = -3825631858416899504L;

	private List<CustInfoBean> items;

	public List<CustInfoBean> getItems() {
		return items;
	}

	public void setItems(List<CustInfoBean> items) {
		this.items = items;
	}
	
}
