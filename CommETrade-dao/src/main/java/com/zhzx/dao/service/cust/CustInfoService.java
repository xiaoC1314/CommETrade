package com.zhzx.dao.service.cust;

import com.zhzx.dao.mapper.cust.CustInfoMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <br>
 * <b>功能：</b>CustInfoService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service
public class CustInfoService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CustInfoService.class);
	

	

	@Autowired
    private CustInfoMapper<T> mapper;

		
	public CustInfoMapper<T> getMapper() {
		return mapper;
	}

}
