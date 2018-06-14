package com.u2u.framework.base;

/**
 * @ClassName: BaseMapper <br>
 * @Description: Mapper 基础类. <br>
 * @date 2015-3-2 下午01:38:30 <br>
 * 
 * @author Dean
 * @param <T>
 * @param <PK>
 */
public interface BaseMapper {
	String OPERATE_AND_CREATE_DATE = " ,CREATE_DATE AS createDate,OPERATE_DATE AS operateDate ";
}
