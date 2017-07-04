package com.zhzx.dao.service.prod;

import com.zhzx.dao.mapper.prod.ProdPlanDetailMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * <br>
 * <b>功能：</b>ProdPlanDetailService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service()
public class ProdPlanDetailService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ProdPlanDetailService.class);
	

	

	@Autowired
    private ProdPlanDetailMapper<T> mapper;

		
	public ProdPlanDetailMapper<T> getMapper() {
		return mapper;
	}

}
