package com.zhzx.dao.service.order;

import com.zhzx.dao.mapper.order.CartMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <br>
 * <b>功能：</b>CartService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service()
public class CartService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CartService.class);
	

	

	@Autowired
    private CartMapper<T> mapper;

		
	public CartMapper<T> getMapper() {
		return mapper;
	}

}
