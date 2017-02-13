package com.u2u.framework.dal.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: PageBreak <br>
 * @Description: Page切割的注解 <br>
 * @date 2015-3-2 下午2:06:15 <br>
 * 
 * @author Freud
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PageBreak
{
    
}
