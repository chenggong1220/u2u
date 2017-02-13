package com.u2u.framework.enums;

/**
 * @ClassName: BaseEnum <br>
 * @Description: 通用枚举接口 <br>
 * @date 2015-1-9 下午05:59:19 <br>
 * 
 * @author dean
 * @param <K>
 */
public interface BaseEnum<K> {
	
	/**
	 * 值信息
	 * @return
	 */
	K getCode();
	
	/**
	 * 描述信息
	 * @return
	 */
	String getDesc();
	
	/**
	 * 显示文本名称
	 * @return
	 */
	String name();
    
}
