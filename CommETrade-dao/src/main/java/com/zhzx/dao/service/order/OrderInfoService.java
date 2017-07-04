package com.zhzx.dao.service.order;

import com.zhzx.dao.mapper.order.OrderInfoMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <br>
 * <b>功能：</b>OrderInfoService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service()
public class OrderInfoService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(OrderInfoService.class);
	

	

	@Autowired
    private OrderInfoMapper<T> mapper;

		
	public OrderInfoMapper<T> getMapper() {
		return mapper;
	}

}
