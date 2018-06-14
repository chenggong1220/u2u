package com.u2u.framework.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

/**
 * @ClassName: CacheHandler <br>
 * @Description: 缓存处理类.默认为manuallycache. <br>
 * @date 2015-1-16 下午03:51:48 <br>
 * 
 * @author Dean
 */
public class EhCacheManager {

	/**
	 * Discription:[清除默认cache.]
	 * 
	 * @author:[Dean]
	 */
	public static void clear() {
		getNativeCache().clear();
	}

	/**
	 * Discription:[清除指定cahche.]
	 * 
	 * @param name
	 *            String
	 * @author:[Dean]
	 */
	public static void clear(final String name) {
		CacheManagerFactory.getCache(name).clear();
	}

	/**
	 * Discription:[获取默认cache中key对应的object.]
	 * 
	 * @param key
	 *            Object
	 * @return ValueWrapper
	 * @author:[Dean]
	 */
	public static ValueWrapper get(final Object key) {
		return getNativeCache().get(key);
	}

	/**
	 * Discription:[获取对应cache的value.]
	 * 
	 * @param name
	 *            cache name
	 * @param key
	 *            key
	 * @return ValueWrapper
	 * @author:[Dean]
	 */
	public static ValueWrapper get(final String name, final Object key) {
		return CacheManagerFactory.getCache(name).get(key);
	}

	/**
	 * Discription:[获取默认缓存名字.]
	 * 
	 * @return String
	 * @author:[Dean]
	 */
	public static String getName() {
		return getNativeCache().getName();
	}

	/**
	 * Discription:[获取配置文件中默认缓存]
	 * 
	 * @return Cache
	 * @author:[Dean]
	 */
	private static Cache getNativeCache() {
		return CacheManagerFactory.getCache();
	}

	/**
	 * Discription:[根据名字获取对应cache.]
	 * 
	 * @param name
	 *            String
	 * @return Object
	 * @author:[Dean]
	 */
	public static Cache getCacheByName(final String name) {
		return CacheManagerFactory.getCache(name);
	}

	/**
	 * Discription:[向默认cache中添加值.]
	 * 
	 * @param key
	 * @param value
	 * @author:[Dean]
	 */
	public static void put(final Object key, final Object value) {
		getNativeCache().put(key, value);

	}

	/**
	 * Discription:[像指定cache中添加值.]
	 * 
	 * @param name
	 *            String
	 * @param key
	 *            Object
	 * @param value
	 *            Object
	 * @author:[Dean]
	 */
	public static void put(final String name, final Object key,
			final Object value) {
		CacheManagerFactory.getCache(name).put(key, value);

	}

	/**
	 * Discription:[删除默认cache中的值.]
	 * 
	 * @param key
	 * @author:[Dean]
	 */
	public void evict(final Object key) {
		getNativeCache().evict(key);
	}

	/**
	 * Discription:[删除对应cache中的值.]
	 * 
	 * @param name
	 *            String
	 * @param key
	 *            Object
	 * @author:[Dean]
	 */
	public void evict(final String name, final Object key) {
		CacheManagerFactory.getCache(name).evict(key);
	}

}
