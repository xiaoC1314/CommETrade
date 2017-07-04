package com.zhzx.uip.api.cust.model;


import com.zhzx.uip.commons.module.GeneralPara;

public class CustInfoPara extends GeneralPara {

	private static final long serialVersionUID = -3554181778752174051L;
    /**
     * 手机号码
     */
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
