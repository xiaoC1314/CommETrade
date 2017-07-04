package com.zhzx.dao.service;

import com.zhzx.dao.mapper.BaseMapper;
import com.zhzx.dao.model.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseService<T> {

	private BaseMapper<T> mapper;

	public BaseMapper<T> getMapper() throws Exception {
		return mapper;
	}

	/**
	 * 插入记录
	 *
	 * @return
	 * @throws Exception
	 */
	public void insert(T t) throws Exception {
		getMapper().insert(t);
	}

	/**
	 * 修改记录
	 *
	 * @return
	 * @throws Exception
	 */
	public void update(T t) throws Exception {
		getMapper().update(t);
	}

	/**
	 * 修改记录，至修改不为空的字段
	 *
	 * @param t
	 * @throws Exception
	 */
	public void updateBySelective(T t) throws Exception {
		getMapper().updateBySelective(t);
	}

	/**
	 * 根据id删除记录
	 *
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String... ids) throws Exception {
		if (ids == null || ids.length < 1) {
			return;
		}
		for (String id : ids) {
			getMapper().delete(id);
		}
	}

	/**
	 * 根据id查找记录记录，返回单条记录
	 *
	 * @return
	 * @throws Exception
	 */
	public T selectById(String id) throws Exception {
		return getMapper().selectById(id);
	}

	/**
	 * 根据model查询总数
	 *
	 * @return
	 * @throws Exception
	 */
	public Integer selectByModelCount(BaseModel model) throws Exception {
		return getMapper().selectByModelCount(model);
	}

	/**
	 * 根据map查询总数
	 *
	 * @return
	 * @throws Exception
	 */
	public Integer selectByMapCount(Map map) throws Exception {
		return getMapper().selectByMapCount(map);
	}

	/**
	 * 根据map查询list记录，不分页
	 *
	 * @return
	 * @throws Exception
	 */
	public List<T> selectByModel(BaseModel model) throws Exception {
		return getMapper().selectByModel(model);
	}

	/**
	 * 根据map查询list记录，分页,默认10条
	 *
	 * @return
	 * @throws Exception
	 */
	public List<T> selectByModelAsPage(BaseModel model) throws Exception {
		Integer rowCount = selectByModelCount(model);
		model.getNavigate().setRowCount(rowCount);
		return getMapper().selectByModelAsPage(model);
	}

	/**
	 * 根据map查询list记录，不分页。
	 *
	 * @return
	 * @throws Exception
	 */
	public List<T> selectByMap(Map map) throws Exception {
		return getMapper().selectByMap(map);
	}

}