/**
 * 
 */
package com.zhzx.uip.commons.utils;

import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class BeanUtils {
    static Logger logger = LoggerFactory.getLogger(BeanUtils.class);
	
	/**
	 * 拷贝源对象的属性到目标对象
	 * @param source	源对象
	 * @param target	目标对象
	 */
	public static <T, V> void copy(T source, V target) {
		if(source == null || target == null)return;
		
		BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
		
		beanCopier.copy(source, target, null);
	}
	
	/**
	 * 拷贝源对象的属性到目标类的实例，并返回目标类的实例
	 * @param source	源对象
	 * @param clazz		目标类
	 * @return
	 */
	public static <T, V> V convert(T source, Class<V> clazz) {
		if(source == null)return null;
		
		BeanCopier beanCopier = BeanCopier.create(source.getClass(), clazz, false);
		
		try {
			V targetObj = clazz.newInstance();
			
			beanCopier.copy(source, targetObj, null);
			
			return targetObj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将源对象列表转换为目标类实例的列表
	 * @param sourceList	源对象列表
	 * @param clazz			目标类
	 * @return
	 */
	public static <T, V> List<V> convertList(List<T> sourceList, Class<V> clazz) {
		if(sourceList == null || sourceList.isEmpty())
			return null;
		
		List<V> targetList = new ArrayList<V>();
		
		BeanCopier beanCopier = null;
		
		for(T sourceObj : sourceList){
			if(beanCopier == null) {
				beanCopier = BeanCopier.create(sourceObj.getClass(), clazz, false);
			}
			
			try {
				V targetObj = clazz.newInstance();
				
				beanCopier.copy(sourceObj, targetObj, null);
				
				targetList.add(targetObj);
			} catch (Exception e) {
                logger.error("异常", e);
			}
		}
		
		return targetList;
	}
	
}
