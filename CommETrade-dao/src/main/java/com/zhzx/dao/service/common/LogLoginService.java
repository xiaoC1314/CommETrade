package com.zhzx.dao.service.common;

import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhzx.dao.mapper.common.LogLoginMapper;

/**
 * 
 * <br>
 * <b>功能：</b>LogLoginService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service()
public class LogLoginService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(LogLoginService.class);
	

	

	@Autowired
    private LogLoginMapper<T> mapper;

		
	public LogLoginMapper<T> getMapper() {
		return mapper;
	}

}
