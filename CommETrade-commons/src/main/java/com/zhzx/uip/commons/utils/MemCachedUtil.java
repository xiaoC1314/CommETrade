package com.zhzx.uip.commons.utils;

import com.zhzx.uip.commons.enums.CacheTimes;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.Transcoder;

/**
 * MemCached 工具类 备注： 还需优化配置文件
 * 
 * 
 */
public class MemCachedUtil {

	public static final Logger logger = LoggerFactory.getLogger(MemCachedUtil.class);

	// 默认设置缓存有效时间 1 天 单位s
	public final static CacheTimes DEFAULT_CACHE_TIME = CacheTimes.ONE_DAY;

	private static MemcachedClient memcachedClient = (MemcachedClient) ContextUtils.getBean("memcachedClient");

	/**
	 * 默认设置memcache 缓存
	 * 
	 * @param key
	 *            缓存的key
	 * @param value
	 *            缓存的value
	 * @return
	 */
	public static boolean setCache(String key, Object value) {
		return setCache(key, value, DEFAULT_CACHE_TIME);
	}

	/**
	 * 设置memcache 缓存包括有效时间
	 * 
	 * @param key
	 *            缓存的key
	 * @param value
	 *            缓存的value
	 * @param cacheTime
	 *            缓存的有效时间
	 * @return
	 */
	public static boolean setCache(String key, Object value, CacheTimes cacheTime) {
		if (StringUtils.isNotBlank(key))
			return setCache(key, value, cacheTime.getTime());

		return false;
	}

	public static boolean setCache(String key, Object value, int exp) {
		try {
			memcachedClient.set(key, exp, value);
		} catch (Exception e) {
			logger.error("设置memcached缓存失败，key:" + key + " value:" + value, e);
			return false;
		}

		return true;
	}

	/**
	 * 获取缓存对象
	 * 
	 * @param key
	 *            缓存的key
	 * @return value
	 */
	public static Object getCache(String key) {
		Object result = null;
		if (StringUtils.isNotBlank(key)) {
			try {
				result = memcachedClient.get(key);
			} catch (Exception e) {
				logger.error("获取memcached缓存失败，key:" + key, e);
				return null;
			}
		}
		return result;
	}

	public static <T> T getCache(String key, Transcoder<T> tc) {
		T result = null;
		if (StringUtils.isNotBlank(key)) {
			try {
				result = memcachedClient.get(key, tc);
			} catch (Exception e) {
				logger.error("获取memcached缓存失败，key:" + key, e);
				return null;
			}
		}
		return result;
	}

	/**
	 * 清空某缓存对象
	 * 
	 * @param key
	 *            缓存的key
	 * @return
	 */
	public static boolean cleanCache(String key) {
		try {
			memcachedClient.delete(key);
		} catch (Exception e) {
			logger.error("清除memcached缓存失败，key:" + key, e);
			return false;
		}

		return true;
	}

	/**
	 * 清空所有缓存对象
	 * 
	 * @return
	 */
	public static boolean cleanAllRemoteCache() {
		try {
			memcachedClient.flush();
		} catch (Exception e) {
			logger.error("flush memcached缓存失败", e);
			return false;
		}

		return true;
	}

}
