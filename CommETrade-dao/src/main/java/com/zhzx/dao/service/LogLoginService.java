package com.zhzx.dao.service;

import com.zhzx.dao.mapper.LogLoginMapper;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
