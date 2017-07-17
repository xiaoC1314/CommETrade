package com.zhzx.dao.service.prod;

import com.zhzx.dao.mapper.prod.ProdPropertyMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * <br>
 * <b>功能：</b>ProdPropertyService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service()
public class ProdPropertyService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ProdPropertyService.class);
	

	

	@Autowired
    private ProdPropertyMapper<T> mapper;

		
	public ProdPropertyMapper<T> getMapper() {
		return mapper;
	}

}
