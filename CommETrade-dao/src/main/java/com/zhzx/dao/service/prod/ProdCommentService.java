package com.zhzx.dao.service.prod;

import com.zhzx.dao.mapper.prod.ProdCommentMapper;
import com.zhzx.dao.service.BaseService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <br>
 * <b>功能：</b>ProdCommentService<br>
 * <b>作者：</b>陈浩<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 */
@Service()
public class ProdCommentService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ProdCommentService.class);
	

	

	@Autowired
    private ProdCommentMapper<T> mapper;

		
	public ProdCommentMapper<T> getMapper() {
		return mapper;
	}

}
