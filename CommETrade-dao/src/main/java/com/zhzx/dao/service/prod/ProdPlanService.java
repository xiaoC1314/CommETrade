package com.zhzx.dao.service.prod;

import com.zhzx.dao.mapper.prod.ProdPlanMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <br>
 * <b>功能：</b>ProdPlanService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service()
public class ProdPlanService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ProdPlanService.class);
	

	

	@Autowired
    private ProdPlanMapper<T> mapper;

		
	public ProdPlanMapper<T> getMapper() {
		return mapper;
	}

}
