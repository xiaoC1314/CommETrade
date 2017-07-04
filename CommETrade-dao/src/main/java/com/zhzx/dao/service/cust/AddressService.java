package com.zhzx.dao.service.cust;

import com.zhzx.dao.mapper.cust.AddressMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * <br>
 * <b>功能：</b>AddressService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service
public class AddressService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(AddressService.class);
	

	

	@Autowired
    private AddressMapper<T> mapper;

		
	public AddressMapper<T> getMapper() {
		return mapper;
	}

}
