package com.zhzx.dao.model;


import com.zhzx.dao.common.Navigate;

import java.util.HashMap;

public class BaseModel {

	/**
	 * 分页导航
	 */
	private Navigate navigate = new Navigate();

	public Navigate getNavigate() {
		return navigate;
	}

	public void setNavigate(Navigate navigate) {
		this.navigate = navigate;
	}

    /**
     * 扩展字段,不记录在表里面
     */
    private HashMap extmap;

    public HashMap getExtmap() {
        return extmap;
    }

    public void setExtmap(HashMap extmap) {
        this.extmap = extmap;
    }

}
