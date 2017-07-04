package com.zhzx.dao;

public enum DataSourceType {
	DATASOURCE_PPF("dataSource_ppf", "支付中心数据源"), DATASOURCE_DS("dataSource_ds",
			"直销数据源"), DATASOURCE_DC("dataSource_dc", "数据中心数据源");

	private DataSourceType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
