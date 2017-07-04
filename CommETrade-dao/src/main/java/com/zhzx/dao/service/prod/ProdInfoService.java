package com.zhzx.dao.service.prod;

import com.zhzx.dao.mapper.prod.ProdInfoMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <br>
 * <b>功能：</b>ProdInfoService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service()
public class ProdInfoService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ProdInfoService.class);
	

	

	@Autowired
    private ProdInfoMapper<T> mapper;

		
	public ProdInfoMapper<T> getMapper() {
		return mapper;
	}

}
