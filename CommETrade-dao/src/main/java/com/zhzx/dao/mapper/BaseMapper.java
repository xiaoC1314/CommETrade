package com.zhzx.dao.mapper;


import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
	
	public void insert(T t) throws Exception;
	
	public void update(T t) throws Exception;
	
	public void updateBySelective(T t) throws Exception;
	
	public void delete(String ids) throws Exception;
	
	
	public T selectById(String Id) throws Exception;
	
	public Integer selectByModelCount(com.zhzx.dao.model.BaseModel map) throws Exception;
	
	public Integer selectByMapCount(Map map) throws Exception;
	
	public List<T> selectByModel(com.zhzx.dao.model.BaseModel model) throws Exception;
	
	public List<T> selectByModelAsPage(com.zhzx.dao.model.BaseModel model) throws Exception;
	
	public List<T> selectByMap(Map map) throws Exception;
	
}
