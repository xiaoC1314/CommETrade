package com.zhzx.uip.commons.module;

import com.zhzx.uip.commons.enums.ErrorEnum;

import java.io.Serializable;

public class ResponseToMa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Object rows;
	private Integer total;

	public ResponseToMa(){

	}

	public ResponseToMa(Integer total,Object rows) {
		if(rows == null)
			rows = "[]";
		this.rows = rows;
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String toString() {
		return "ResponseToMa [total=" + total + ", rows=" + rows +  "]";
	}

}
