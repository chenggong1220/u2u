package com.u2u.framework.util;

import java.util.Map;

/**
 * @ClassName: MapUtils <br>
 * @Description: map工具类 <br>
 * @date 2015-1-15 上午10:32:29 <br>
 * 
 * @author Dean
 */
public class MapUtils {
	
    /**
     * <p>Discription:[if key or value is null there will throw exception.]</p>
     * @param map Map<Object,Object>
     * @param key Object
     * @param defaultValue Object
     * @author:[Dean]
     */
	public static void putIfNull(Map<Object,Object> map, Object key, Object defaultValue) {
		if (key == null)
			throw new IllegalArgumentException("key must be not null");
		if (map == null)
			throw new IllegalArgumentException("map must be not null");
		if (map.get(key) == null) {
			map.put(key, defaultValue);
		}
	}

}
