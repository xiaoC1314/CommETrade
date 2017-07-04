package com.zhzx.uip.commons.enums;

/**
 * 
 * @author zhangtingrui
 * NT:netTrade
 * 0开头: 通用
 * 1开头：账户类
 * 2开头：产品类
 * 3开头：预约码相关
 * 4开头：交易类
 * 5开头：银行卡类
 * 7开头：查询类
 */
public enum AgencyNoEnum {
		ZHONGL("302","众禄360"),
		ZHONGGUO_YINH("004","中国银行"),
		ZHONGTAI_ZHENGQ("660","中泰证券"),
		ZHAOSHANG_YINH("007","招商银行"),
		ZHIXIAO("726","资管直销"),
		TIANTIAN_JIJIN("303","天天基金"),
		XINGYE_JIJIN("011","兴业银行"),
		GUANGDA_YINH("012","光大银行"),
		WEIZHONG_YINH("918","微众银行"),
		JIMU_JIJIN("371","积木基金"),
		QIANJING_JINGD("332","钱景京东"),
		NUOYA_ZHENGX("301","诺亚正行");

	private String agencyno;
	private String agencyname;
	AgencyNoEnum(String agencyno, String agencyname) {
		this.agencyno = agencyno;
		this.agencyname = agencyname;
	}

	public String getAgencyno() {
		return agencyno;
	}

	public void setAgencyno(String agencyno) {
		this.agencyno = agencyno;
	}

	public String getAgencyname() {
		return agencyname;
	}

	public void setAgencyname(String agencyname) {
		this.agencyname = agencyname;
	}
}
