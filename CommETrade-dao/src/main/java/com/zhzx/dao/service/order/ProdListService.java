package com.zhzx.dao.service.order;

import com.zhzx.dao.mapper.order.ProdListMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <br>
 * <b>功能：</b>ProdListService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service()
public class ProdListService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ProdListService.class);
	

	

	@Autowired
    private ProdListMapper<T> mapper;

		
	public ProdListMapper<T> getMapper() {
		return mapper;
	}

}
